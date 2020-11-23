package com.mnp.store.api.controllers;

import com.mnp.store.contracts.authorization.AccountService;
import com.mnp.store.contracts.authorization.dtos.LoginRequestDto;
import com.mnp.store.contracts.authorization.dtos.LoginResponseDto;
import com.mnp.store.contracts.authorization.dtos.RefreshTokenDto;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.RegisterUserRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("auth/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        LoginResponseDto response = accountService.login(loginRequest, responseHeaders);
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @PostMapping("auth/refresh")
    public ResponseEntity<RefreshTokenDto> refresh(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        RefreshTokenDto response = accountService.refreshToken(refreshToken, responseHeaders);
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @GetMapping("auth/jwt")
    public ResponseEntity<String> jwt() {
        return ResponseEntity.ok("");
    }

    @PostMapping("account/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterUserRequestDto request) {
        userService.register(request);
    }
}
