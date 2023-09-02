package com.delivery.storeadmin.converter;

import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.StoreMenu;
import com.delivery.storeadmin.model.StoreMenuResponse;

import java.util.List;

@Converter
public class StoreMenuConverter {
    public StoreMenuResponse toResponse(StoreMenu storeMenu) {
        return StoreMenuResponse.builder()
                .id(storeMenu.getId())
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
