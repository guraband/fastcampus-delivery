package com.delivery.api.controller;

import com.delivery.api.business.UserBusiness;
import com.delivery.api.model.UserLoginRequest;
import com.delivery.api.model.UserRequest;
import com.delivery.api.model.UserResponse;
import com.delivery.common.dto.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {
    private final UserBusiness userBusiness;

    @PostMapping("/register")
    public CommonResponse<UserResponse> register(@Valid @RequestBody UserRequest request) {
        var response = userBusiness.register(request);
        return CommonResponse.ok(response);
    }

    @PostMapping("/login")
    public CommonResponse<UserResponse> login(@Valid @RequestBody UserLoginRequest request) {
        var response = userBusiness.login(request);
        return CommonResponse.ok(response);
    }
}
