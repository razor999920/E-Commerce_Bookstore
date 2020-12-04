package com.mnp.store.contracts.users;

import java.util.Optional;

import com.mnp.store.contracts.dtos.BuyingStatistics;
import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;

import com.mnp.store.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponseDto> listAllUsers(Pageable pageable);

    Optional<UserResponseDto> getUserById(long id);

    Optional<UserResponseDto> getUserByUsername(String username);

    Optional<UserResponseDto> getUserByEmail(String email);

    Optional<UserResponseDto> getUserByLogin(String login);

    UserResponseDto createUser(CreateUserRequestDto userInfo);

    void deleteUser(String username);

    Optional<String> getCurrentLogin();

    Optional<User> getCurrentUser();

    Page<User> findAllWithRolesAndReviews(Pageable pageable);

    Page<User> findAll(Pageable pageable);

    User save(User user);

    BuyingStatistics getBuyingStatistics();
}

