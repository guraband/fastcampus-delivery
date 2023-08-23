package com.delivery.api.controller;

import com.delivery.api.business.StoreMenuBusiness;
import com.delivery.api.model.StoreMenuRequest;
import com.delivery.api.model.StoreMenuResponse;
import com.delivery.common.annotation.UserSession;
import com.delivery.common.dto.CommonResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-menu")
public class StoreMenuController {
    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public CommonResponse<StoreMenuResponse> register(
            @Valid @RequestBody StoreMenuRequest request,
            @Parameter(hidden = true) @UserSession Long userId
    ) {
        var response = storeMenuBusiness.register(request, userId);
        return CommonResponse.ok(response);
    }
}
