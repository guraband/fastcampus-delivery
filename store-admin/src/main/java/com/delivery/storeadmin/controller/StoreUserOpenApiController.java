package com.delivery.storeadmin.controller;

import com.delivery.api.model.UserLoginRequest;
import com.delivery.common.dto.CommonResponse;
import com.delivery.common.dto.TokenResponse;
import com.delivery.storeadmin.model.StoreUserRequest;
import com.delivery.storeadmin.model.StoreUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store-user")
public class StoreUserOpenApiController {
    private final StoreUserBusiness storeUserBusiness;

    @PostMapping("/register")
    public StoreUserResponse register(@Valid @RequestBody StoreUserRequest request) {
        var response = userBusiness.register(request);
        return CommonResponse.ok(response);
    }

    @PostMapping("/login")
    public CommonResponse<TokenResponse> login(@Valid @RequestBody UserLoginRequest request) {
        var response = userBusiness.login(request);
        return CommonResponse.ok(response);
    }
}
