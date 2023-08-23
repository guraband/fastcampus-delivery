package com.delivery.db.repository;

import com.delivery.db.entity.UserOrderMenu;
import com.delivery.db.enums.UserOrderMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderMenuRepository extends JpaRepository<UserOrderMenu, Long> {
    List<UserOrderMenu> findAllByUserOrderIdAndStatusOrderById(Long userOrderId, UserOrderMenuStatus status);
}
