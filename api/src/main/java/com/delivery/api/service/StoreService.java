package com.delivery.api.service;

import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.db.entity.Store;
import com.delivery.db.enums.StoreCategory;
import com.delivery.db.enums.StoreStatus;
import com.delivery.db.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    // 스토어 조회
    public Store getOrThrow(Long id) {
        return Optional.ofNullable(
                storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
        ).orElseThrow(() -> new ApiException(ErrorStatusCode.SERVER_ERROR));
    }

    // 스토어 등록
    @Transactional
    public Store register(Store store) {
        return Optional.ofNullable(store)
                .map(storeRepository::save)
                .orElseThrow(() -> new ApiException(ErrorStatusCode.NULL_POINT, "Store Entity is null"));
    }

    // 카테고리로 스토어 조회
    public List<Store> findByCategory(StoreCategory category) {
        return storeRepository.findAllByStatusAndCategoryOrderByStarDesc(StoreStatus.REGISTERED, category);
    }

    // 전체 스토어 조회
    public List<Store> getStores() {
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}
