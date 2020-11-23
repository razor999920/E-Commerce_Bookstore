package com.mnp.store.contracts.authorization.dtos;

public class LoginResponseDto {
    private LoginStatus status;
    private String message;

    public LoginResponseDto() {
    }

    public LoginResponseDto(LoginStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public LoginStatus getStatus() {
        return status;
    }

    public void setStatus(LoginStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
