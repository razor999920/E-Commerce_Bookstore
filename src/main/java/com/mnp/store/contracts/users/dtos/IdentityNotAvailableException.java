package com.mnp.store.contracts.users.dtos;

public class IdentityNotAvailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdentityNotAvailableException(String identityName, String identityValue) {
        super(String.format("Identity [%s]: %s is already used.", identityName, identityValue));
    }
}


