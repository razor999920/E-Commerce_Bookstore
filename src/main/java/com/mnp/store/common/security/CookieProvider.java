package com.mnp.store.common.security;

import com.mnp.store.common.security.jwt.JwtTokenProvider;
import com.mnp.store.common.security.jwt.JwtTokenType;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CookieProvider {
    JwtTokenProvider tokenProvider;

    public CookieProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public HttpCookie createAccessTokenCookie(String token, long duration) {
        return createTokenCookie(token, duration, JwtTokenType.ACCESS_TOKEN);
    }

    public HttpCookie createRefreshTokenCookie(String token, long duration) {
        return createTokenCookie(token, duration, JwtTokenType.REFRESH_TOKEN);
    }

    public HttpCookie deleteAccessTokenCookie() {
        return ResponseCookie.from(JwtTokenType.ACCESS_TOKEN.name(), "")
                .maxAge(0)
                .httpOnly(true)
                .path("/")
                .build();
    }

    public HttpCookie deleteRefreshTokenCookie() {
        return ResponseCookie.from(JwtTokenType.REFRESH_TOKEN.name(), "")
                .maxAge(0)
                .httpOnly(true)
                .path("/")
                .build();
    }

    private HttpCookie createTokenCookie(String token, long duration, JwtTokenType tokenType) {
        String encryptedToken = tokenProvider.encrypt(token);
        return ResponseCookie.from(tokenType.name(), encryptedToken)
                .maxAge(duration)
                .httpOnly(true)
                .path("/")
                .build();
    }
}
