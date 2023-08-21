package com.delivery.api.model;

import com.delivery.db.enums.StoreCategory;
import com.delivery.db.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {
    private Long id;

    private String name;

    private String address;

    private StoreStatus status;

    private StoreCategory category;

    private double star;

    private String thumbnailUrl;

    private BigDecimal minimumAmount;

    private BigDecimal minimumDeliveryAmount;

    private String phoneNumber;
}
