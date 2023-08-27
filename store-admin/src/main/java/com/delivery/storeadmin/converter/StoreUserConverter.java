package com.delivery.storeadmin.converter;

import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.Store;
import com.delivery.db.entity.StoreUser;
import com.delivery.db.enums.StoreUserStatus;
import com.delivery.storeadmin.model.StoreUserRequest;
import com.delivery.storeadmin.model.StoreUserResponse;
import com.delivery.storeadmin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Converter
public class StoreUserConverter {
    private final PasswordEncoder passwordEncoder;
    private final StoreService storeService;

    public StoreUser toEntity(StoreUserRequest request) {
        storeService.getOrThrow(request.getStoreId());

        return StoreUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(StoreUserStatus.REGISTERED)
                .role(request.getRole())
                .storeId(request.getStoreId())
                .build();
    }

    public StoreUserResponse toResponse(StoreUser storeUser, Store store) {
        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(storeUser.getId())
                                .email(storeUser.getEmail())
                                .status(storeUser.getStatus())
                                .role(storeUser.getRole())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(store.getId())
                                .name(store.getName())
                                .build()
                )
                .build();
    }
}
