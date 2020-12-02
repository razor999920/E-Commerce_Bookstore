package com.mnp.store.common.security.jwt;

import java.time.LocalDateTime;

public class JwtToken {
    private JwtTokenType tokenType;
    private String tokenValue;
    private Long duration;
    private LocalDateTime expiryDate;

    public JwtToken(JwtTokenType tokenType, String tokenValue, Long duration, LocalDateTime expiryDate) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
        this.duration = duration;
        this.expiryDate = expiryDate;
    }

    public JwtTokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(JwtTokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
