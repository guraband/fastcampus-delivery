package com.delivery.api.controller;

import com.delivery.api.business.StoreBusiness;
import com.delivery.api.model.StoreRequest;
import com.delivery.api.model.StoreResponse;
import com.delivery.common.dto.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store")
public class StoreOpenApiController {
    private final StoreBusiness storeBusiness;

    @PostMapping("")
    public CommonResponse<StoreResponse> register(
            @Valid @RequestBody StoreRequest request
    ) {
        var response = storeBusiness.register(request);
        return CommonResponse.ok(response);
    }
}
