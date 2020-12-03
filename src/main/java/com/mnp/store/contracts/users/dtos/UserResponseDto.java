package com.mnp.store.contracts.users.dtos;

public class UserResponseDto {
    private String username;
    private String email;
    private boolean emailConfirmed;

    public UserResponseDto() {
    }

    public UserResponseDto(String username, String email, boolean emailConfirmed) {
        this.username = username;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }
}

