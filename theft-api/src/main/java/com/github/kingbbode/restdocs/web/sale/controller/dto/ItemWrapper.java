package com.github.kingbbode.restdocs.web.sale.controller.dto;

import com.github.kingbbode.restdocs.domain.sale.SaleItem;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemWrapper {
    private final List<SaleItem> algumon;

    public ItemWrapper(List<SaleItem> items) {
        this.algumon = items;
    }
}
