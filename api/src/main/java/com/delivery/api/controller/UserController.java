package com.delivery.api.controller;

import com.delivery.api.business.UserBusiness;
import com.delivery.api.model.UserResponse;
import com.delivery.common.annotation.UserSession;
import com.delivery.common.dto.CommonResponse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public CommonResponse<UserResponse> getMe(@Parameter(hidden = true) @UserSession Long id) {
        return CommonResponse.ok(userBusiness.getMe(id));
    }
}
