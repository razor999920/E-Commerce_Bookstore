package com.mnp.store.common.security.exceptions;

public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException() {
        super("Password does not match");
    }
}
