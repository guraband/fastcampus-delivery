package com.delivery.api.business;

import com.delivery.api.converter.StoreConverter;
import com.delivery.api.model.StoreRequest;
import com.delivery.api.model.StoreResponse;
import com.delivery.api.service.StoreService;
import com.delivery.common.annotation.Business;
import com.delivery.db.enums.StoreCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreBusiness {
    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRequest request, Long userId) {
        var entity = storeConverter.toEntity(request, userId);
        var newEntity = storeService.register(entity);
        return storeConverter.toResponse(newEntity);
    }

    public List<StoreResponse> searchByCategory(StoreCategory category) {
        return storeService.findByCategory(category)
                .stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }
}
