package com.delivery.api.business;

import com.delivery.api.converter.UserOrderConverter;
import com.delivery.api.converter.UserOrderMenuConverter;
import com.delivery.api.model.UserOrderRequest;
import com.delivery.api.model.UserOrderResponse;
import com.delivery.api.service.StoreMenuService;
import com.delivery.api.service.UserOrderMenuService;
import com.delivery.api.service.UserOrderService;
import com.delivery.common.annotation.Business;
import com.delivery.common.exception.ApiException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;

    private final UserOrderService userOrderService;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;

    public UserOrderResponse order(UserOrderRequest request, Long userId) {
        var storeMenus = storeMenuService.getAllByIds(request.getStoreMenuIds());
        if (storeMenus.isEmpty() || storeMenus.size() != request.getStoreMenuIds().size()) {
            throw ApiException.nullError();
        }

        var userOrder = userOrderService.order(
                userOrderConverter.toEntity(userId, storeMenus)
        );
        var userOrderMenus = storeMenus.stream()
                .map(storeMenu -> userOrderMenuConverter.toEntity(userOrder, storeMenu))
                .toList();
        userOrderMenuService.order(userOrderMenus);

        return userOrderConverter.toResponse(userOrder);
    }
}
