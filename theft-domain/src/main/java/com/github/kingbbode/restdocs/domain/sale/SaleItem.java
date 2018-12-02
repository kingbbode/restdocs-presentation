package com.github.kingbbode.restdocs.domain.sale;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String postId;

    private String title;

    private String link;

    private String price;

    @Builder
    public SaleItem(String postId, String title, String link, String price) {
        this.postId = postId;
        this.title = title;
        this.link = link;
        this.price = price;
    }
}
