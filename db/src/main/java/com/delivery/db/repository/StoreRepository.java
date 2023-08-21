package com.delivery.db.repository;

import com.delivery.db.entity.Store;
import com.delivery.db.enums.StoreCategory;
import com.delivery.db.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findFirstByIdAndStatusOrderByIdDesc(long id, StoreStatus status);

    List<Store> findAllByStatusOrderByIdDesc(StoreStatus status);

    List<Store> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus status, StoreCategory category);
}
