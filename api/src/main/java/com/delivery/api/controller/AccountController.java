package com.delivery.api.controller;

import com.delivery.api.common.dto.CommonResponse;
import com.delivery.api.model.AccountResponse;
import com.delivery.db.entity.Account;
import com.delivery.db.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public CommonResponse<AccountResponse> me() {
        return CommonResponse.ok(
                AccountResponse.builder()
                        .name("홍길동")
                        .createdAt(LocalDateTime.now())
                        .build());
    }

    @PostMapping("")
    public <T> CommonResponse<T> save() {
        accountRepository.save(new Account());
        return CommonResponse.ok();
    }
}
