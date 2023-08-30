package com.delivery.api.business;

import com.delivery.api.converter.StoreConverter;
import com.delivery.api.converter.StoreMenuConverter;
import com.delivery.api.converter.UserOrderConverter;
import com.delivery.api.converter.UserOrderMenuConverter;
import com.delivery.api.model.UserOrderDetailResponse;
import com.delivery.api.model.UserOrderRequest;
import com.delivery.api.model.UserOrderResponse;
import com.delivery.api.rabbitmq.UserOrderProducer;
import com.delivery.api.service.StoreMenuService;
import com.delivery.api.service.StoreService;
import com.delivery.api.service.UserOrderMenuService;
import com.delivery.api.service.UserOrderService;
import com.delivery.common.annotation.Business;
import com.delivery.common.exception.ApiException;
import com.delivery.db.entity.UserOrder;
import com.delivery.db.entity.UserOrderMenu;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final StoreMenuConverter storeMenuConverter;
    private final StoreConverter storeConverter;

    private final UserOrderService userOrderService;
    private final UserOrderMenuService userOrderMenuService;
    private final StoreMenuService storeMenuService;
    private final StoreService storeService;

    private final UserOrderProducer userOrderProducer;

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

        userOrderProducer.sendOrder(userOrder);

        return userOrderConverter.toResponse(userOrder);
    }

    public List<UserOrderDetailResponse> current(Long userId) {
        var userOrders = userOrderService.getCurrentUserOrders(userId);

        return toUserOrderDetailResponses(userOrders);
    }

    public List<UserOrderDetailResponse> history(Long userId) {
        var userOrders = userOrderService.getFinishedUserOrders(userId);

        return toUserOrderDetailResponses(userOrders);
    }

    public UserOrderDetailResponse detail(Long userId, Long orderId) {
        var userOrder = userOrderService.getOrThrow(orderId, userId);
        return toUserOrderDetailResponse(userOrder);
    }

    private UserOrderDetailResponse toUserOrderDetailResponse(UserOrder userOrder) {
        var userOderMenus = userOrderMenuService.getUserOrderMenus(userOrder.getId());
        var storeMenuIds = userOderMenus.stream()
                .map(UserOrderMenu::getStoreMenuId)
                .toList();
        var storeMenus = storeMenuService.getAllByIds(storeMenuIds);
        var store = storeService.getOrThrow(storeMenus.get(0).getStoreId());

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrder))
                .storeResponse(storeConverter.toResponse(store))
                .storeMenuResponses(storeMenuConverter.toResponse(storeMenus))
                .build();
    }

    private List<UserOrderDetailResponse> toUserOrderDetailResponses(List<UserOrder> userOrders) {
        return userOrders.stream()
                .map(this::toUserOrderDetailResponse)
                .toList();
    }
}
