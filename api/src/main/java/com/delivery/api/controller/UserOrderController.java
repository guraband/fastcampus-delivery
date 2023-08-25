package com.delivery.api.controller;

import com.delivery.api.business.UserOrderBusiness;
import com.delivery.api.model.UserOrderDetailResponse;
import com.delivery.api.model.UserOrderRequest;
import com.delivery.api.model.UserOrderResponse;
import com.delivery.common.annotation.UserSession;
import com.delivery.common.dto.CommonResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 현재 진행중인 주문건
    @GetMapping("/current")
    public CommonResponse<List<UserOrderDetailResponse>> current(
            @Parameter(hidden = true) @UserSession Long userId
    ) {
        var response = userOrderBusiness.current(userId);
        return CommonResponse.ok(response);
    }

    // 과거 주문건
    @GetMapping("/history")
    public CommonResponse<List<UserOrderDetailResponse>> history(
            @Parameter(hidden = true) @UserSession Long userId
    ) {
        var response = userOrderBusiness.history(userId);
        return CommonResponse.ok(response);
    }

    // 과거 주문건
    @GetMapping("/id/{orderId}")
    public CommonResponse<UserOrderDetailResponse> detail(
            @PathVariable Long orderId,
            @Parameter(hidden = true) @UserSession Long userId
    ) {
        var response = userOrderBusiness.detail(userId, orderId);
        return CommonResponse.ok(response);
    }
}
