package com.delivery.db.entity;

import com.delivery.db.enums.UserOrderMenuStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserOrderMenu extends BaseEntity {
    @JoinColumn(nullable = false)
    @ManyToOne
    private UserOrder userOrder;

    @JoinColumn(nullable = false)
    @ManyToOne
    private StoreMenu storeMenu;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderMenuStatus status;
}
