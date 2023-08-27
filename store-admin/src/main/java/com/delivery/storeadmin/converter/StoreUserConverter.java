package com.delivery.storeadmin.converter;

import com.delivery.api.model.UserResponse;
import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.StoreUser;
import com.delivery.db.entity.User;
import com.delivery.db.enums.StoreUserStatus;
import com.delivery.storeadmin.model.StoreUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Converter
public class StoreUserConverter {
    private final PasswordEncoder passwordEncoder;

    public StoreUser toEntity(StoreUserRequest request) {
        

        return StoreUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(StoreUserStatus.REGISTERED)
                .role(request.getRole())
                .storeId()
                .build();
    }

    public UserResponse toResponse(User entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .lastLoginAt(entity.getLastLoginAt())
                .build();
    }
}
