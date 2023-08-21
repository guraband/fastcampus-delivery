package com.delivery.api.controller;

import com.delivery.api.business.StoreBusiness;
import com.delivery.api.model.StoreResponse;
import com.delivery.common.dto.CommonResponse;
import com.delivery.db.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final StoreBusiness storeBusiness;

    @GetMapping("/search")
    public CommonResponse<List<StoreResponse>> search(
            @RequestParam StoreCategory category
    ) {
        return CommonResponse.ok(storeBusiness.searchByCategory(category));
    }
}
