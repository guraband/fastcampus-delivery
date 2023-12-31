package com.delivery.api.controller;

import com.delivery.api.business.StoreBusiness;
import com.delivery.api.model.StoreRequest;
import com.delivery.api.model.StoreResponse;
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
@RequestMapping("/api/store")
public class StoreController {
    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    public CommonResponse<StoreResponse> register(
            @Valid @RequestBody StoreRequest request,
            @Parameter(hidden = true) @UserSession Long userId
    ) {
        var response = storeBusiness.register(request, userId);
        return CommonResponse.ok(response);
    }
}
