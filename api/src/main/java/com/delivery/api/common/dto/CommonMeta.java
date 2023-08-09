package com.delivery.api.common.dto;

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
                .code(200)
                .message("OK")
                .description("성공")
                .build();
    }
}
