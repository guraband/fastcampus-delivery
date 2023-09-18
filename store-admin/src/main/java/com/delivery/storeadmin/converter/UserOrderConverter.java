package com.delivery.storeadmin.converter;

import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.UserOrder;
import com.delivery.storeadmin.model.UserOrderResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class UserOrderConverter {
    public UserOrderResponse toResponse(UserOrder entity) {
        return UserOrderResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .storeId(entity.getStore().getId())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .orderedAt(entity.getOrderedAt())
                .acceptedAt(entity.getAcceptedAt())
                .cookingStartedAt(entity.getCookingStartedAt())
                .deliveryStartedAt(entity.getDeliveryStartedAt())
                .receivedAt(entity.getReceivedAt())
                .build();
    }
}
