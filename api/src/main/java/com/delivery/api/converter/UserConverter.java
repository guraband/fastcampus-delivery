package com.delivery.api.converter;

import com.delivery.api.model.UserRequest;
import com.delivery.api.model.UserResponse;
import com.delivery.common.annotation.Converter;
import com.delivery.db.entity.User;
import com.delivery.db.enums.UserStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class UserConverter {
    public User toEntity(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .status(UserStatus.REGISTERED)
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
