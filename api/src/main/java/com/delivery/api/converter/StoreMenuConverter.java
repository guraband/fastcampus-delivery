package com.delivery.api.converter;

import com.delivery.api.model.StoreMenuRequest;
import com.delivery.api.model.StoreMenuResponse;
import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.Store;
import com.delivery.db.entity.StoreMenu;
import com.delivery.db.enums.StoreMenuStatus;

import java.util.List;

@Converter
public class StoreMenuConverter {
    public StoreMenu toEntity(StoreMenuRequest request, Store store, Long userId) {
        return StoreMenu.builder()
                .store(store)
                .name(request.getName())
                .amount(request.getAmount())
                .thumbnailUrl(request.getThumbnailUrl())
                .sortOrder(request.getSortOrder())
                .status(StoreMenuStatus.REGISTERED)
                .createdBy(userId)
                .build();
    }

    public StoreMenuResponse toResponse(StoreMenu storeMenu) {
        return StoreMenuResponse.builder()
                .id(storeMenu.getId())
                .storeId(storeMenu.getStore().getId())
                .name(storeMenu.getName())
                .status(storeMenu.getStatus())
                .amount(storeMenu.getAmount())
                .thumbnailUrl(storeMenu.getThumbnailUrl())
                .likeCount(storeMenu.getLikeCount())
                .sortOrder(storeMenu.getSortOrder())
                .build();
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenu> storeMenus) {
        return storeMenus.stream().map(this::toResponse).toList();
    }
}
