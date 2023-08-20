package com.delivery.common.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TokenErrorStatusCode implements StatusCode {
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), 2001, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST.value(), 2002, "만료된 토큰"),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 2003, "토큰 정보 없음"),
    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST.value(), 2100, "토큰 오류");;

    private final Integer httpStatusCode;
    private final Integer code;
    private final String message;
}
