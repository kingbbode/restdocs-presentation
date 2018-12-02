package com.github.kingbbode.restdocs.web.sale.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SaleResponse {
    private int count;

    @Builder
    public SaleResponse(int count) {
        this.count = count;
    }
}
