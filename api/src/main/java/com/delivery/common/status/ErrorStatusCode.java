package com.delivery.common.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorStatusCode implements StatusCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null point"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 521, "존재하지 않는 회원");

    private final Integer httpStatusCode;
    private final Integer code;
    private final String message;
}
