package com.mnp.store.services.users;

import com.mnp.store.common.security.exceptions.PasswordMismatchException;
import com.mnp.store.common.security.exceptions.UserExistsException;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.RegisterUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import com.mnp.store.domain.authorization.Role;
import com.mnp.store.domain.authorization.RoleConstants;
import com.mnp.store.domain.users.User;
import com.mnp.store.domain.users.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto register(RegisterUserRequestDto request) {

        if (!request.getPassword().equals(request.getPasswordConfirm()))
            throw new PasswordMismatchException();

        userRepository.findOneByUsernameOrEmailIgnoreCase(request.getUsername(), request.getEmail())
                .ifPresent(u -> {
                    throw new UserExistsException(request.getUsername());
                });

        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        newUser.setUsername(request.getUsername().toLowerCase());
        newUser.setEmail(request.getEmail().toLowerCase());
        newUser.setPassword(encryptedPassword);

        // TODO: default activated should be false
        newUser.setActivated(true);

        Set<Role> roles = new HashSet<>();
        roles.add(RoleConstants.admin());
        roles.add(RoleConstants.user());

        newUser.setRoles(roles);
        userRepository.save(newUser);
        return new UserResponseDto(newUser);
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDto> list(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserResponseDto::new);
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDto> getCurrentUser() {
        return getCurrentUsername().flatMap(userRepository::findOneByUsernameIgnoreCase).map(UserResponseDto::new);
    }

    private static Optional<String> getCurrentUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails)
                return Optional.ofNullable(((UserDetails) principal).getUsername());
            if (principal instanceof String)
                return Optional.of((String) principal);
        }

        return Optional.empty();
    }
}
