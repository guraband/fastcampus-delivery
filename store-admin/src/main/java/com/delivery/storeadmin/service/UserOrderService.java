package com.delivery.storeadmin.service;

import com.delivery.db.entity.UserOrder;
import com.delivery.db.repository.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public Optional<UserOrder> getUserOrder(Long id) {
        return userOrderRepository.findById(id);
    }
}
