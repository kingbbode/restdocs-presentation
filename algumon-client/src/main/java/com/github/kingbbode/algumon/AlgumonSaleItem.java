package com.github.kingbbode.algumon;

import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class AlgumonSaleItem {

    public static final String NOT_EXIST_PRICE = "정보 없음";
    private String title;

    private String link;

    private String price;

    @Builder
    public AlgumonSaleItem(String title, String link, String price) {
        this.title = title;
        this.link = link;
        this.price = Optional.ofNullable(price).orElse(NOT_EXIST_PRICE);
    }

    @Override
    public String toString() {
        return "AlgumonSaleItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
