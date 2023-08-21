package com.delivery.db.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreCategory {
    KOREAN("한식"),
    CHINESE("중식"),
    WESTERN("양식"),
    JAPANESE("일식"),
    CHICKEN("치킨"),
    ITALIAN("이탈리안"),
    COFFEE("커피"),
    ;

    private final String description;
}
