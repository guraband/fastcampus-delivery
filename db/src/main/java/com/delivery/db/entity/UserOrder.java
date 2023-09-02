package com.delivery.db.entity;

import com.delivery.db.enums.UserOrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserOrder extends BaseEntity {
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long storeId;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column
    private LocalDateTime orderedAt;

    @Column
    private LocalDateTime acceptedAt;

    @Column
    private LocalDateTime cookingStartedAt;

    @Column
    private LocalDateTime deliveryStartedAt;

    @Column
    private LocalDateTime receivedAt;

    public void changeStatus(UserOrderStatus status) {
        this.status = status;

        switch (status) {
            case ORDER -> this.orderedAt = LocalDateTime.now();
            case COOKING -> this.cookingStartedAt = LocalDateTime.now();
            case DELIVERY -> this.deliveryStartedAt = LocalDateTime.now();
            case RECEIVED -> this.receivedAt = LocalDateTime.now();
            case ACCEPT -> this.acceptedAt = LocalDateTime.now();
        }
    }
}
