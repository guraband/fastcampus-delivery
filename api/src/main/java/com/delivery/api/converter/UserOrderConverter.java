package com.delivery.api.converter;

import com.delivery.api.model.UserOrderResponse;
import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.StoreMenu;
import com.delivery.db.entity.UserOrder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Converter
public class UserOrderConverter {
    public UserOrder toEntity(Long userId, List<StoreMenu> storeMenus) {
        var totalAmount = storeMenus.stream()
                .map(StoreMenu::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return UserOrder.builder()
                .userId(userId)
                .amount(totalAmount)
                .createdBy(userId)
                .build();
    }

    public UserOrderResponse toResponse(UserOrder entity) {
        return UserOrderResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .orderedAt(entity.getOrderedAt())
                .build();
    }
}
