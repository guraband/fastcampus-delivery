package com.delivery.api.business;

import com.delivery.api.converter.StoreMenuConverter;
import com.delivery.api.model.StoreMenuRequest;
import com.delivery.api.model.StoreMenuResponse;
import com.delivery.api.service.StoreMenuService;
import com.delivery.api.service.StoreService;
import com.delivery.common.annotation.Business;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {
    private final StoreService storeService;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(StoreMenuRequest request, Long userId) {
        var store = storeService.getOrThrow(request.getStoreId());
        var entity = storeMenuConverter.toEntity(request, store, userId);
        var newEntity = storeMenuService.register(entity);
        return storeMenuConverter.toResponse(newEntity);
    }

    public List<StoreMenuResponse> searchByStoreId(Long storeId) {
        return storeMenuService.getStoreMenus(storeId)
                .stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
    }
}
