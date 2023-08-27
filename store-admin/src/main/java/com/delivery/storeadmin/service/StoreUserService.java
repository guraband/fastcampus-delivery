package com.delivery.storeadmin.service;

import com.delivery.db.entity.StoreUser;
import com.delivery.db.entity.User;
import com.delivery.db.enums.StoreUserStatus;
import com.delivery.db.repository.StoreUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class StoreUserService {
    private final StoreUserRepository storeUserRepository;

    private final PasswordEncoder passwordEncoder;

    public StoreUser register(StoreUser storeUser) {
        return storeUserRepository.save(storeUser);
    }

    public Optional<StoreUser> getStoreUser(String email) {
        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email, StoreUserStatus.REGISTERED);
    }

    public User login(String email, String password) {
        return getUserOrThrow(email, password);
    }
}
