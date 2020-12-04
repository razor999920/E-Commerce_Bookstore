package com.mnp.store.common.errors;

import java.net.URI;

public final class ErrorConstants {
    public static final String PROBLEM_BASE_URL = "https://mnp.com/problem";
    public static final URI INVALID_CREDENTIAL_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-credentials");
    public static final URI IDENTITY_ALREADY_EXISTS_TYPE = URI.create(PROBLEM_BASE_URL + "/identity-already-exists");
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/generic-problem");
}
