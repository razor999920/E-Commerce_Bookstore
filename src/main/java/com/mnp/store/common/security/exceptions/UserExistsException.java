package com.mnp.store.common.security.exceptions;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String username) {
        super(String.format("User '%s' already exists.", username));
    }
}
