package com.mnp.store.contracts.users.dtos;

import com.mnp.store.domain.Role;

import java.util.Set;

public class UserResponseDto {
    private String username;
    private String email;
    private boolean emailConfirmed;
    private Set<Role> roles;

    public UserResponseDto() {
    }

    public UserResponseDto(String username, String email, boolean emailConfirmed, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.emailConfirmed = emailConfirmed;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

