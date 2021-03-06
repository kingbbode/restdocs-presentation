package com.github.kingbbode.algumon;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class AlgumonClient {

    private static final String ALGUMON_URL = "https://algumon.com/more/0?types=ended";
    private static final String POST_LI_CLASS = "post-li";
    private static final String PRODUCT_BODY_CLASS = ".product-body";
    private static final String TITLE_ATTR_KEY = "data-label";
    private static final String LINK_ATTR_KEY = "href";

    public List<AlgumonSaleItem> parse() {
        try {
            Document document = Jsoup.connect(ALGUMON_URL).get();
            return document.body()
                    .select("li")
                    .stream()
                    .filter(li -> li.hasClass(POST_LI_CLASS))
                    .map(this::createDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("[ALGUMON][PARSE][ERROR] {}, {}", ALGUMON_URL, e.getMessage());
            log.debug("[ALGUMON][PARSE][ERROR] {}", e);
            return Collections.emptyList();
        }
    }

    private AlgumonSaleItem createDto(Element product) {

        AlgumonSaleItem.AlgumonSaleItemBuilder builder = AlgumonSaleItem.builder();

        builder.postId(product.attr("id"));

        product.select(PRODUCT_BODY_CLASS)
                .forEach(element -> {
                    fillItemInfo(element, builder);
                    fillPrice(element, builder);
                });

        return builder.build();
    }

    private void fillItemInfo(Element product, AlgumonSaleItem.AlgumonSaleItemBuilder builder) {
        Optional.ofNullable(product.select(".item-name a").first())
                .map(Element::attributes)
                .ifPresent(attr -> {
                    builder.title(attr.get(TITLE_ATTR_KEY));
                    builder.link(attr.get(LINK_ATTR_KEY));
                });
    }

    private void fillPrice(Element product, AlgumonSaleItem.AlgumonSaleItemBuilder builder) {
        Optional.ofNullable(product.select(".product-price").first())
                .map(Node::childNodes)
                .filter(nodes -> !CollectionUtils.isEmpty(nodes))
                .map(nodes -> nodes.get(0))
                .ifPresent(node -> builder.price(node.toString()));
    }
}
