package com.delivery.api.common.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessStatusCode implements StatusCode {
    OK(HttpStatus.OK.value(), 200, "성공");

    private final Integer httpStatusCode;
    private final Integer code;
    private final String message;
}
