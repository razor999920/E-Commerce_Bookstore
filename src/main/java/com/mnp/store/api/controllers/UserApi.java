package com.mnp.store.api.controllers;

import com.mnp.store.common.http.ResponseUtils;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Page<UserResponseDto> get(Pageable pageable) {
        return userService.listAllUsers(pageable);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody CreateUserRequestDto userInfo) {
        return new ResponseEntity<>(userService.createUser(userInfo), HttpStatus.CREATED);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDto> getByUsername(@PathVariable String username) {
        return ResponseUtils.ofOptional(userService.getUserByUsername(username));
    }

    @DeleteMapping("/users/{username}")
    public void delete(@PathVariable String username) {
        userService.deleteUser(username);
    }
}

