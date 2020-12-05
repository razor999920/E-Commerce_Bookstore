package com.mnp.store.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.mnp.store.contracts.dtos.BuyingStatistics;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import com.mnp.store.contracts.users.dtos.IdentityAlreadyExistsException;
import com.mnp.store.domain.Role;
import com.mnp.store.domain.constants.RoleConstants;
import com.mnp.store.domain.User;
import com.mnp.store.domain.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.mapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDto> listAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(u -> mapper.map(u, UserResponseDto.class));
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDto> getUserById(long id) {
        return userRepository.findById(id).map(u -> mapper.map(u, UserResponseDto.class));
    }

    public UserResponseDto createUser(CreateUserRequestDto userInfo) {

        userRepository.findOneByUsernameIgnoreCase(userInfo.getUsername()).ifPresent(u -> {
            throw new IdentityAlreadyExistsException("username", userInfo.getUsername());
        });

        userRepository.findOneByEmailIgnoreCase(userInfo.getEmail()).ifPresent(u -> {
            throw new IdentityAlreadyExistsException("email", userInfo.getEmail());
        });

        User newUser = new User();
        mapper.map(userInfo, newUser);

        // TODO: remove this
        Set<Role> roles = new HashSet<>();
        roles.add(RoleConstants.user());
        roles.add(RoleConstants.admin());
        newUser.setRoles(roles);

        newUser.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        return mapper.map(userRepository.save(newUser), UserResponseDto.class);
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDto> getUserByUsername(String username) {
        return userRepository.findOneByUsernameIgnoreCase(username).map(u -> mapper.map(u, UserResponseDto.class));
    }

    @Override
    public Optional<UserResponseDto> getUserByEmail(String email) {
        return userRepository.findOneByEmailIgnoreCase(email).map(u -> mapper.map(u, UserResponseDto.class));
    }

    @Override
    public Optional<UserResponseDto> getUserByLogin(String login) {
        return userRepository.findOneWithRolesByEmailOrUsernameIgnoreCase(login, login)
                .map(u -> mapper.map(u, UserResponseDto.class));
    }

    public void deleteUser(String username) {
        userRepository.findOneByUsernameIgnoreCase(username).ifPresent(userRepository::delete);
    }

    public Optional<String> getCurrentLogin() {
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication auth = sc.getAuthentication();

        if (auth == null)
            return Optional.empty();

        Object principal = auth.getPrincipal();

        if (principal instanceof UserDetails) {
            return Optional.ofNullable(((UserDetails) principal).getUsername());
        }

        if (principal instanceof String) {
            return Optional.of((String) principal);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getCurrentUser() {
        Optional<String> login = getCurrentLogin();
        if (!login.isPresent())
            return Optional.empty();

        return userRepository.findOneByUsernameOrEmailIgnoreCase(login.get(), login.get());
    }

    @Override
    public Page<User> findAllWithRolesAndReviews(Pageable pageable) {
        return userRepository.findAllWithRolesAndReviews(pageable);
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public BuyingStatistics getBuyingStatistics() {
        return userRepository.getBuyingStatistics();
    }
}