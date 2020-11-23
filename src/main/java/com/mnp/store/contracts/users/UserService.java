package com.mnp.store.contracts.users;

import com.mnp.store.contracts.users.dtos.RegisterUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import com.mnp.store.domain.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserResponseDto register(RegisterUserRequestDto request);
    Page<UserResponseDto> list(Pageable pageable);
    Optional<UserResponseDto> getCurrentUser();
}
