package com.github.kingbbode.restdocs.web.sale.controller;

import com.github.kingbbode.restdocs.web.common.CommonResponse;
import com.github.kingbbode.restdocs.web.sale.controller.dto.SaleResponse;
import com.github.kingbbode.restdocs.web.sale.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/sales")
@RequiredArgsConstructor
@RestController
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public CommonResponse<SaleResponse> crowl() {
        return CommonResponse.ok(
                SaleResponse.builder()
                    .count(saleService.crowl())
                    .build()
        );
    }
}
