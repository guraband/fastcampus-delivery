package com.delivery.common.exception;

import com.delivery.common.status.ErrorStatusCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final ErrorStatusCode errorStatusCode;

    private final String message;

    public ApiException(ErrorStatusCode errorStatusCode) {
        super(errorStatusCode.getMessage());
        this.errorStatusCode = errorStatusCode;
        this.message = errorStatusCode.getMessage();
    }

    public ApiException(ErrorStatusCode errorStatusCode, String message) {
        super(errorStatusCode.getMessage());
        this.errorStatusCode = errorStatusCode;
        this.message = message;
    }

    public ApiException(ErrorStatusCode errorStatusCode, Throwable t) {
        super(t);
        this.errorStatusCode = errorStatusCode;
        this.message = errorStatusCode.getMessage();
    }
}
