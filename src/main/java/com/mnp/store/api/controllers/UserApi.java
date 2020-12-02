package com.mnp.store.api.controllers;

import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApi {

    private final UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> list(Pageable pageable) {
        final Page<UserResponseDto> page = userService.list(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/users/current")
    public UserResponseDto current() {
        return userService.getCurrentUser().orElseThrow(() -> new UsernameNotFoundException("TODO"));
    }
}
