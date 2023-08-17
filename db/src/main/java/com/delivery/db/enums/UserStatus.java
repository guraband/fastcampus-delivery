package com.delivery.db.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지");

    private final String description;
}
