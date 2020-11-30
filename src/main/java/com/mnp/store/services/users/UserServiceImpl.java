package com.mnp.store.services.users;

import java.util.Optional;

import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import com.mnp.store.contracts.users.dtos.IdentityNotAvailableException;
import com.mnp.store.domain.users.User;
import com.mnp.store.domain.users.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            throw new IdentityNotAvailableException("username", userInfo.getUsername());
        });

        userRepository.findOneByEmailIgnoreCase(userInfo.getEmail()).ifPresent(u -> {
            throw new IdentityNotAvailableException("email", userInfo.getEmail());
        });

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        mapper.map(userInfo, newUser);

        return mapper.map(userRepository.save(newUser), UserResponseDto.class);
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDto> getUserByUsername(String username) {
        return userRepository.findOneByUsernameIgnoreCase(username).map(u -> mapper.map(u, UserResponseDto.class));
    }

    public void deleteUser(String username) {
        userRepository.findOneByUsernameIgnoreCase(username).ifPresent(u -> {
            userRepository.delete(u);
        });
    }
}