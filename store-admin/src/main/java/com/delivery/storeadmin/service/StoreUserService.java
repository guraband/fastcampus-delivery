package com.delivery.storeadmin.service;

import com.delivery.db.entity.StoreUser;
import com.delivery.db.repository.StoreUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class StoreUserService {
    private final StoreUserRepository storeUserRepository;

    public StoreUser register(StoreUser storeUser) {
        return storeUserRepository.save(storeUser);
    }
/*
    public Optional<StoreUser> getStoreUser(String email) {
        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED);
    }

    public User login(String email, String password) {
        return getUserOrThrow(email, password);
    }
    */
}
