package com.delivery.api.converter;

import com.delivery.api.model.StoreMenuRequest;
import com.delivery.api.model.StoreMenuResponse;
import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.StoreMenu;
import com.delivery.db.enums.StoreMenuStatus;

import java.util.List;

@Converter
public class StoreMenuConverter {
    public StoreMenu toEntity(StoreMenuRequest request, Long userId) {
        return StoreMenu.builder()
                .storeId(request.getStoreId())
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
                .storeId(storeMenu.getStoreId())
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
