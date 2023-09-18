package com.delivery.storeadmin.business;

import com.delivery.common.annotation.Business;
import com.delivery.common.dto.message.UserOrderMessage;
import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.db.entity.UserOrderMenu;
import com.delivery.storeadmin.component.SseConnectionPool;
import com.delivery.storeadmin.converter.StoreMenuConverter;
import com.delivery.storeadmin.converter.UserOrderConverter;
import com.delivery.storeadmin.model.StoreUserSseConnection;
import com.delivery.storeadmin.model.UserOrderDetailResponse;
import com.delivery.storeadmin.service.StoreMenuService;
import com.delivery.storeadmin.service.UserOrderMenuService;
import com.delivery.storeadmin.service.UserOrderService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {
    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;

    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    private final SseConnectionPool<String, StoreUserSseConnection> sseConnectionPool;

    public void pushUserOrder(UserOrderMessage userOrderMessage) {
        var userOrder = userOrderService.getUserOrder(userOrderMessage.getUserOrderId())
                .orElseThrow(() -> new ApiException(ErrorStatusCode.NULL_POINT));

        // 주문 정보 조회
        var userOrderMenus = userOrderMenuService.getUserOrderMenus(userOrder.getId());

        var storeMenus = userOrderMenus.stream()
                .map(UserOrderMenu::getStoreMenu)
                .toList();
        var storeMenuResponses = storeMenus.stream()
                .map(storeMenuConverter::toResponse)
                .toList();

        var userOrderDetailResponse = UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrder))
                .storeMenuResponses(storeMenuResponses)
                .build();

        var storeUserSession = sseConnectionPool.getSession(String.valueOf(userOrder.getStore().getId()));
        if (storeUserSession != null) {
            storeUserSession.sendMessage(userOrderDetailResponse);
        }
    }
}
