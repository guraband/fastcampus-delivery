package com.delivery.api.common.dto;

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
}
