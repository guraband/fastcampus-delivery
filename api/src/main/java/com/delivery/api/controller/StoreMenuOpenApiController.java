package com.delivery.api.controller;

import com.delivery.api.business.StoreMenuBusiness;
import com.delivery.api.model.StoreMenuResponse;
import com.delivery.common.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store-menu")
public class StoreMenuOpenApiController {
    private final StoreMenuBusiness storeMenuBusiness;
    
    @GetMapping("/search")
    public CommonResponse<List<StoreMenuResponse>> search(
            @RequestParam Long storeId
    ) {
        return CommonResponse.ok(storeMenuBusiness.searchByStoreId(storeId));
    }
}
