package com.mnp.store.contracts.authorization.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RefreshTokenDto {
    @JsonIgnore
    private boolean success;
    private String message;

    public RefreshTokenDto() {}

    public RefreshTokenDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
