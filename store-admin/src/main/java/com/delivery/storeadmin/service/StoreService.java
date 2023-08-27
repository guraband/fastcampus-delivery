package com.delivery.storeadmin.service;

import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.db.entity.Store;
import com.delivery.db.enums.StoreStatus;
import com.delivery.db.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    // 스토어 조회
    public Store getOrThrow(Long id) {
        return storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorStatusCode.SERVER_ERROR));
    }
}
