package com.delivery.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserOrderRequest {
    @NotNull
    private List<Long> storeMenuIds;
}
