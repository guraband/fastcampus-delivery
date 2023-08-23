package com.delivery.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class StoreMenuRequest {
    @NotNull
    private Long storeId;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String thumbnailUrl;

    private int sortOrder;
}
