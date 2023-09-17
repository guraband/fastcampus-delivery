package com.delivery.api.service;

import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.db.entity.StoreMenu;
import com.delivery.db.enums.StoreMenuStatus;
import com.delivery.db.repository.StoreMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreMenuService {
    private final StoreMenuRepository storeMenuRepository;

    // 스토어 조회
    public StoreMenu getOrThrow(Long id) {
        return Optional.ofNullable(storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
        ).orElseThrow(() -> new ApiException(ErrorStatusCode.SERVER_ERROR));
    }

    public List<StoreMenu> getAllByIds(List<Long> storeMenuIds) {
        return storeMenuRepository.findAllByIdInAndStatus(storeMenuIds, StoreMenuStatus.REGISTERED);
    }

    // 특정 스토어의 메뉴 목록 조회
    public List<StoreMenu> getStoreMenus(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySortOrder(storeId, StoreMenuStatus.REGISTERED);
    }

    // 스토어 메뉴 등록
    @Transactional
    public StoreMenu register(StoreMenu storeMenu) {
        return Optional.ofNullable(storeMenu)
                .map(storeMenuRepository::save)
                .orElseThrow(() -> new ApiException(ErrorStatusCode.NULL_POINT, "Store Entity is null"));
    }
}
