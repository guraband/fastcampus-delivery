package com.delivery.storeadmin.controller;

import com.delivery.common.dto.CommonResponse;
import com.delivery.storeadmin.converter.StoreUserConverter;
import com.delivery.storeadmin.model.StoreUserResponse;
import com.delivery.storeadmin.model.StoreUserSession;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-user")
public class StoreUserController {
    private final StoreUserConverter storeUserConverter;

    @GetMapping("/me")
    public CommonResponse<StoreUserResponse> me(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserSession storeUserSession
    ) {
        return CommonResponse.ok(storeUserConverter.toResponse(storeUserSession));
    }
}
