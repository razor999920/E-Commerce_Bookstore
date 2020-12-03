package com.mnp.store.contracts.users.dtos;

import com.mnp.store.common.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class IdentityAlreadyExistsException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public IdentityAlreadyExistsException(String identityType, String identityValue) {

        super(ErrorConstants.IDENTITY_ALREADY_EXISTS_TYPE,
                "Identity already exists",
                Status.BAD_REQUEST,
                String.format("%s '%s' already exists", identityType, identityValue));
    }
}


