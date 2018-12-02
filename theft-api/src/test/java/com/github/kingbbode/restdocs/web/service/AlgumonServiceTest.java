package com.github.kingbbode.restdocs.web.service;

import com.github.kingbbode.algumon.AlgumonSaleItem;
import com.github.kingbbode.restdocs.domain.sale.SaleItemRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgumonServiceTest {

    @Autowired
    private AlgumonService algumonService;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @After
    public void tearDown() throws Exception {
        saleItemRepository.deleteAllInBatch();
    }

    @Test
    public void 중복_회피_저장() {
        //given
        List<AlgumonSaleItem> firstItems = Arrays.asList(
                AlgumonSaleItem.builder().postId("2").title("맥북 40%").link("kingbbode.com").build(),
                AlgumonSaleItem.builder().postId("3").title("맥북 30%").link("kingbbode.com").build()
        );
        List<AlgumonSaleItem> secondItems = Arrays.asList(
                AlgumonSaleItem.builder().postId("1").title("맥북 50%").link("kingbbode.com").build(),
                AlgumonSaleItem.builder().postId("2").title("맥북 40%").link("kingbbode.com").build(),
                AlgumonSaleItem.builder().postId("3").title("맥북 30%").link("kingbbode.com").build()
        );

        //when
        algumonService.save(firstItems);
        algumonService.save(secondItems);

        //then
        assertEquals(3, saleItemRepository.findAll().size());
    }
}
