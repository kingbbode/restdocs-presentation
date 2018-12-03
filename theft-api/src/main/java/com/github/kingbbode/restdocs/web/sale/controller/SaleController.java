package com.github.kingbbode.restdocs.web.sale.controller;

import com.github.kingbbode.restdocs.domain.sale.SaleItem;
import com.github.kingbbode.restdocs.domain.sale.SaleItemRepository;
import com.github.kingbbode.restdocs.web.common.CommonResponse;
import com.github.kingbbode.restdocs.web.sale.controller.dto.ItemWrapper;
import com.github.kingbbode.restdocs.web.sale.controller.dto.SaleRequest;
import com.github.kingbbode.restdocs.web.sale.controller.dto.SaleResponse;
import com.github.kingbbode.restdocs.web.sale.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/sales")
@RequiredArgsConstructor
@RestController
public class SaleController {

    private final SaleService saleService;
    private final SaleItemRepository saleItemRepository;

    @PostMapping
    public CommonResponse<SaleResponse> crowl() {
        return CommonResponse.ok(
                SaleResponse.builder()
                    .count(saleService.crowl())
                    .build()
        );
    }

    @GetMapping
    public CommonResponse<ItemWrapper> get(SaleRequest saleRequest) {
        List<SaleItem> items = saleItemRepository.findAll();
        return CommonResponse.ok(
                new ItemWrapper(items)
        );
    }
}
