package com.delivery.storeadmin.converter;

import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.StoreMenu;
import com.delivery.db.entity.UserOrder;
import com.delivery.db.entity.UserOrderMenu;
import com.delivery.db.enums.UserOrderMenuStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class UserOrderMenuConverter {
    public UserOrderMenu toEntity(UserOrder userOrder, StoreMenu storeMenu) {
        return UserOrderMenu.builder()
                .userOrder(userOrder)
                .storeMenu(storeMenu)
                .status(UserOrderMenuStatus.REGISTERED)
                .createdBy(userOrder.getCreatedBy())
                .build();
    }
}
