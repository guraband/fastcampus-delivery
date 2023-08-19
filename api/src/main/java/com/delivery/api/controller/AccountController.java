package com.delivery.api.controller;

import com.delivery.api.model.AccountResponse;
import com.delivery.common.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    @GetMapping("/me")
    public CommonResponse<AccountResponse> me() {
        return CommonResponse.ok(
                AccountResponse.builder()
                        .name("홍길동")
                        .createdAt(LocalDateTime.now())
                        .build());
    }
}
