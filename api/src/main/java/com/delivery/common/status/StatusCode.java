package com.delivery.common.status;

public interface StatusCode {
    Integer getHttpStatusCode();

    Integer getCode();

    String getMessage();
}
