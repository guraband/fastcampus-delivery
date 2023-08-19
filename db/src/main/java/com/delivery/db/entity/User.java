package com.delivery.db.entity;

import com.delivery.db.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    private LocalDateTime lastLoginAt;
}
