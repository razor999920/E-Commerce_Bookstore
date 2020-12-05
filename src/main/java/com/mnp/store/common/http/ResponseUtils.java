package com.mnp.store.common.http;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ResponseUtils {
    public static <T> ResponseEntity<T> ofOptional(Optional<T> value) {
        return ofOptional(value, null);
    }

    public static <T> ResponseEntity<T> ofOptional(Optional<T> value, HttpHeaders httpHeaders) {
        return value.map(r -> ResponseEntity.ok().headers(httpHeaders).body(r))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
