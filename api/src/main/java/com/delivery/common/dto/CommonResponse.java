package com.delivery.common.dto;

import com.delivery.common.status.ErrorStatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonResponse<T> {
    public CommonResponse(CommonMeta meta) {
        this.meta = meta;
    }

    public CommonResponse(CommonMeta meta, T body) {
        this.meta = meta;
        this.body = body;
    }

    private CommonMeta meta;

    private T body;

    public static <T> CommonResponse<T> ok() {
        return new CommonResponse<>(CommonMeta.ok());
    }

    public static <T> CommonResponse<T> ok(T body) {
        return new CommonResponse<>(CommonMeta.ok(), body);
    }

    public static CommonResponse<Object> ok(CommonMeta meta) {
        return new CommonResponse<>(meta);
    }

    public static CommonResponse<Object> error(ErrorStatusCode errorStatusCode) {
        return new CommonResponse<>(CommonMeta.error(errorStatusCode));
    }

    public static CommonResponse<Object> error(ErrorStatusCode errorStatusCode, Throwable tx) {
        return new CommonResponse<>(CommonMeta.error(errorStatusCode, tx));
    }

    public static CommonResponse<Object> error(ErrorStatusCode errorStatusCode, String description) {
        return new CommonResponse<>(CommonMeta.error(errorStatusCode, description));
    }
}
