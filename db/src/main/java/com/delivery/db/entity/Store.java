package com.delivery.db.entity;

import com.delivery.db.enums.StoreCategory;
import com.delivery.db.enums.StoreStatus;
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

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Store extends BaseEntity {
    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private double star;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumAmount;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal minimumDeliveryAmount;

    @Column(length = 20, nullable = false)
    private String phoneNumber;
}
