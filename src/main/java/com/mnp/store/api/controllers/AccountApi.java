package com.mnp.store.api.controllers;

import com.mnp.store.contracts.authorization.AccountService;
import com.mnp.store.contracts.authorization.dtos.LoginRequestDto;
import com.mnp.store.contracts.authorization.dtos.RefreshTokenDto;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.CreateUserRequestDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountApi {

    private final AccountService accountService;
    private final UserService userService;

    public AccountApi(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }


    @PostMapping("/auth/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        UserResponseDto response = accountService.login(loginRequest, responseHeaders);
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        accountService.logout(request, response, responseHeaders);
        return ResponseEntity.ok().headers(responseHeaders).build();
    }


    @PostMapping("/auth/refresh")
    public ResponseEntity<RefreshTokenDto> refresh(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        RefreshTokenDto response = accountService.refreshToken(refreshToken, responseHeaders);
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @GetMapping("/auth/jwt")
    public ResponseEntity<String> jwt() {
        // TODO
        return ResponseEntity.ok("");
    }

    @GetMapping("/auth")
    public ResponseEntity<Boolean> isLoggedIn() {
        return ResponseEntity.ok(accountService.isAuthenticated());
    }

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody CreateUserRequestDto request) {
        userService.createUser(request);
    }
}
