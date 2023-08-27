package com.delivery.storeadmin.business;

import com.delivery.common.annotation.Business;
import com.delivery.storeadmin.converter.StoreUserConverter;
import com.delivery.storeadmin.model.StoreUserRequest;
import com.delivery.storeadmin.model.StoreUserResponse;
import com.delivery.storeadmin.service.StoreService;
import com.delivery.storeadmin.service.StoreUserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class StoreUserBusiness {
    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;
    private final StoreService storeService;

    public StoreUserResponse register(StoreUserRequest request) {
        var entity = storeUserConverter.toEntity(request);
        var newEntity = storeUserService.register(entity);
        var store = storeService.getOrThrow(request.getStoreId());
        return storeUserConverter.toResponse(newEntity, store);
    }
}
