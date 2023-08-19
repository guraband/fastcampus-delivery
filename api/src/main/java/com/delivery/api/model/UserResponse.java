package com.delivery.api.model;

import com.delivery.db.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;

    private String email;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;
}
