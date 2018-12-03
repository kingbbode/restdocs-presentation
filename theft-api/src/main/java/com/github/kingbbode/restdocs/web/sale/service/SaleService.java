package com.github.kingbbode.restdocs.web.sale.service;

import com.github.kingbbode.algumon.AlgumonClient;
import com.github.kingbbode.algumon.AlgumonSaleItem;
import com.github.kingbbode.restdocs.domain.sale.SaleItem;
import com.github.kingbbode.restdocs.domain.sale.SaleItemRepository;
import com.github.kingbbode.restdocs.domain.sale.Site;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SaleService {

    private final AlgumonClient algumonClient;
    private final SaleItemRepository saleItemRepository;

    public int crowl() {
        List<AlgumonSaleItem> parse = algumonClient.parse();
        save(parse);
        return saleItemRepository.findAll().size();
    }

    void save(List<AlgumonSaleItem> items) {
        items.forEach(this::save);
    }

    private void save(AlgumonSaleItem item) {
        try {
            saleItemRepository.save(SaleItem.builder()
                    .site(Site.ALGUMON)
                    .postId(item.getPostId())
                    .title(item.getTitle())
                    .link(item.getLink())
                    .price(item.getPrice())
                    .build()
            );
        } catch(DataIntegrityViolationException e) {
            log.debug("[SaleItem][Save][Filter] duplicated postId = {}", item.getPostId());
        }
    }
}
