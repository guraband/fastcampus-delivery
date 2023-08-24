package com.delivery.db.repository;

import com.delivery.db.entity.StoreMenu;
import com.delivery.db.enums.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreMenuRepository extends JpaRepository<StoreMenu, Long> {
    // 유효한 메뉴 1개
    Optional<StoreMenu> findFirstByIdAndStatusOrderByIdDesc(long id, StoreMenuStatus status);

    // 메뉴 여러개 조회
    List<StoreMenu> findAllByIdInAndStatus(List<Long> id, StoreMenuStatus status);

    // 특정 가게의 메뉴
    List<StoreMenu> findAllByStoreIdAndStatusOrderBySortOrder(long storeId, StoreMenuStatus status);
}
