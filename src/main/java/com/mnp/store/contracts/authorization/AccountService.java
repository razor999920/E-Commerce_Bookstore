package com.mnp.store.contracts.authorization;

import com.mnp.store.contracts.authorization.dtos.LoginRequestDto;
import com.mnp.store.contracts.authorization.dtos.RefreshTokenDto;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    UserResponseDto login(LoginRequestDto request, HttpHeaders httpHeaders);

    void logout(HttpServletRequest request, HttpServletResponse response, HttpHeaders httpHeaders);

    RefreshTokenDto refreshToken(String refreshToken, HttpHeaders httpHeaders);

    boolean isAuthenticated();
}
