package com.mnp.store.contracts.authorization;

import com.mnp.store.contracts.authorization.dtos.LoginRequestDto;
import com.mnp.store.contracts.authorization.dtos.LoginResponseDto;
import com.mnp.store.contracts.authorization.dtos.RefreshTokenDto;
import org.springframework.http.HttpHeaders;

public interface AccountService {

    LoginResponseDto login(LoginRequestDto request, HttpHeaders httpHeaders);

    RefreshTokenDto refreshToken(String refreshToken, HttpHeaders httpHeaders);
}
