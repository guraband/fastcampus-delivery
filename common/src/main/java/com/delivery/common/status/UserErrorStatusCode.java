package com.delivery.common.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorStatusCode implements StatusCode {
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 1001, "존재하지 않는 회원");

    private final Integer httpStatusCode;
    private final Integer code;
    private final String message;
}
