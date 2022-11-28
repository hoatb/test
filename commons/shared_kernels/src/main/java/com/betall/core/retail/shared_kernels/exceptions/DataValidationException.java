package com.betall.core.retail.shared_kernels.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class DataValidationException extends RuntimeException {
    public DataValidationException(String message) {
        super(message);
    }
}
