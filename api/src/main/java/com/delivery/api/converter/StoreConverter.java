package com.delivery.api.converter;

import com.delivery.api.model.StoreRequest;
import com.delivery.api.model.StoreResponse;
import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.Store;

@Converter
public class StoreConverter {
    public Store toEntity(StoreRequest request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .category(request.getCategory())
                .minimumAmount(request.getMinimumAmount())
                .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                .thumbnailUrl(request.getThumbnailUrl())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public StoreResponse toResponse(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .status(store.getStatus())
                .category(store.getCategory())
                .minimumAmount(store.getMinimumAmount())
                .minimumDeliveryAmount(store.getMinimumDeliveryAmount())
                .thumbnailUrl(store.getThumbnailUrl())
                .phoneNumber(store.getPhoneNumber())
                .star(store.getStar())
                .build();
    }
}
