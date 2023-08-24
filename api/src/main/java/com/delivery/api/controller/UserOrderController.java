package com.delivery.api.controller;

import com.delivery.api.business.UserOrderBusiness;
import com.delivery.api.model.UserOrderRequest;
import com.delivery.api.model.UserOrderResponse;
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
@RequestMapping("/api/user-order")
public class UserOrderController {

    private final UserOrderBusiness userOrderBusiness;

    @PostMapping("")
    public CommonResponse<UserOrderResponse> userOrder(
            @Valid @RequestBody UserOrderRequest request,
            @Parameter(hidden = true) @UserSession Long userId
    ) {
        return CommonResponse.ok(
                userOrderBusiness.order(request, userId)
        );
    }
}
