package com.betall.core.retail.haravanretail.exceptions;

import com.betall.core.retail.shared_kernels.exceptions.HaravanRequestException;
import com.betall.core.retail.shared_kernels.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.WebUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.betall.core.retail.shared_kernels.exceptions.DataViolationException;
import com.betall.core.retail.shared_kernels.exceptions.DatabasePopulateException;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
        DatabasePopulateException.class,
        DataViolationException.class,
        ResourceNotFoundException.class,
        Exception.class
    })
    public ResponseEntity<ApiError> globalExceptionHandler(final Exception e, final WebRequest request) {
        final HttpHeaders headers = new HttpHeaders();

        if (e instanceof DatabasePopulateException) {
            final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e instanceof DataViolationException) {
            final ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (e instanceof HaravanRequestException) {
            final ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
            return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            if (log.isWarnEnabled()) {
                log.warn("Unknown exception type: " + e.getClass().getName());
            }

            final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return handleExceptionInternal(e, apiError, headers, status, request);
        }
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(final Exception e, final ApiError body, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
