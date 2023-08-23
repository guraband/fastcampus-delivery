package com.delivery.api.service;

import com.delivery.db.entity.UserOrderMenu;
import com.delivery.db.enums.UserOrderMenuStatus;
import com.delivery.db.repository.UserOrderMenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserOrderMenuService {
    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenu> getUserOrderMenus(Long userOrderId) {
        return userOrderMenuRepository.findAllByUserOrderIdAndStatusOrderById(userOrderId, UserOrderMenuStatus.REGISTERED);
    }
}
