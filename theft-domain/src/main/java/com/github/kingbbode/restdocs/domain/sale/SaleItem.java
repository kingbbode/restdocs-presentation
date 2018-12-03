package com.github.kingbbode.restdocs.domain.sale;

import com.github.kingbbode.restdocs.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Site site;

    @Column(unique = true)
    private String postId;

    private String title;

    private String link;

    private String price;

    @Builder
    public SaleItem(Site site, String postId, String title, String link, String price) {
        this.site = site;
        this.postId = postId;
        this.title = title;
        this.link = link;
        this.price = price;
    }
}
