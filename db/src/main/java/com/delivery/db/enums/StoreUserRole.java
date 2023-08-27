package com.delivery.db.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreUserRole {
    ADMIN("관리자"),
    MASTER("마스터"),
    USER("유저");

    private final String description;
}
