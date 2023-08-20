package com.delivery.common.exception;

import com.delivery.common.status.StatusCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final StatusCode errorStatusCode;

    private final String message;

    public ApiException(StatusCode errorStatusCode) {
        super(errorStatusCode.getMessage());
        this.errorStatusCode = errorStatusCode;
        this.message = errorStatusCode.getMessage();
    }

    public ApiException(StatusCode errorStatusCode, String message) {
        super(errorStatusCode.getMessage());
        this.errorStatusCode = errorStatusCode;
        this.message = message;
    }

    public ApiException(StatusCode errorStatusCode, Throwable t) {
        super(t);
        this.errorStatusCode = errorStatusCode;
        this.message = errorStatusCode.getMessage();
    }
}
