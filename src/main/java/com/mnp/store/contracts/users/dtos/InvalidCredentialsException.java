package com.mnp.store.contracts.users.dtos;

import com.mnp.store.common.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidCredentialsException extends AbstractThrowableProblem {

    public InvalidCredentialsException() {
        super(ErrorConstants.INVALID_CREDENTIAL_TYPE, "Invalid credentials", Status.BAD_REQUEST);
    }
}
