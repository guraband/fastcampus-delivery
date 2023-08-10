package com.delivery.api.common.status;

public interface StatusCode {
    Integer getHttpStatusCode();

    Integer getCode();

    String getMessage();
}
