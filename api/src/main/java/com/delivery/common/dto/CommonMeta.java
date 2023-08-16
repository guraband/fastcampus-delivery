package com.delivery.common.dto;

import com.delivery.common.status.ErrorStatusCode;
import com.delivery.common.status.SuccessStatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonMeta {
    private Integer code;

    private String message;

    private String description;

    public static CommonMeta ok() {
        return CommonMeta.builder()
                .code(SuccessStatusCode.OK.getCode())
                .message(SuccessStatusCode.OK.getMessage())
                .description("성공")
                .build();
    }

    public static CommonMeta error(ErrorStatusCode errorStatusCode) {
        return CommonMeta.builder()
                .code(errorStatusCode.getCode())
                .message(errorStatusCode.getMessage())
                .build();
    }

    public static CommonMeta error(ErrorStatusCode errorStatusCode, Throwable tx) {
        return CommonMeta.builder()
                .code(errorStatusCode.getCode())
                .message(errorStatusCode.getMessage())
                .description(tx.getLocalizedMessage())
                .build();
    }

    public static CommonMeta error(ErrorStatusCode errorStatusCode, String description) {
        return CommonMeta.builder()
                .code(errorStatusCode.getCode())
                .message(errorStatusCode.getMessage())
                .description(description)
                .build();
    }
}
