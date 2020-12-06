package com.mnp.store.domain.exceptions;

import com.mnp.store.common.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadRequestException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public BadRequestException(String defaultMessage) {
        super(ErrorConstants.DEFAULT_TYPE, defaultMessage, Status.BAD_REQUEST);
    }
}


