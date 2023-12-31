package com.delivery.db.entity;

import com.delivery.db.enums.StoreMenuStatus;
import jakarta.persistence.*;
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
public class StoreMenu extends BaseEntity {
    @JoinColumn(nullable = false)
    @ManyToOne
    private Store store;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column
    private int likeCount;

    @Column
    private int sortOrder;

    public void init() {
        this.status = StoreMenuStatus.REGISTERED;
    }
}
