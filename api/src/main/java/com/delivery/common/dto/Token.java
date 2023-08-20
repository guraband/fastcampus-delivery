package com.delivery.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Token {
    private String token;

    private LocalDateTime expiredAt;
}
