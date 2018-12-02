package com.github.kingbbode.algumon;

import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class AlgumonClientTest {

    @Test
    public void 파싱_테스트() {
        //given
        AlgumonClient algumonClient = new AlgumonClient();

        //when
        List<AlgumonSaleItem> results = algumonClient.parse();

        //then
        assertFalse(CollectionUtils.isEmpty(results));
        results.forEach(result -> {
            assertFalse(StringUtils.isEmpty(result.getTitle()));
            assertFalse(StringUtils.isEmpty(result.getLink()));
            assertFalse(StringUtils.isEmpty(result.getPrice()));
            System.out.println(result);
        });
    }
}
