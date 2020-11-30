package com.mnp.store.contracts.users;

import java.util.Optional;

import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponseDto> listAllUsers(Pageable pageable);

    Optional<UserResponseDto> getUserById(long id);

    Optional<UserResponseDto> getUserByUsername(String username);

    UserResponseDto createUser(CreateUserRequestDto userInfo);

    void deleteUser(String username);
}

