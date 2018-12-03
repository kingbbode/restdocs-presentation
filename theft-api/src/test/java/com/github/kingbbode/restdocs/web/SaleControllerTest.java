package com.github.kingbbode.restdocs.web;

import com.github.kingbbode.algumon.AlgumonSaleItem;
import com.github.kingbbode.restdocs.RestdocsPresentaionTestContext;
import com.github.kingbbode.restdocs.domain.sale.SaleItem;
import com.github.kingbbode.restdocs.domain.sale.SaleItemRepository;
import com.github.kingbbode.restdocs.domain.sale.Site;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.github.kingbbode.restdocs.RestDocsUtils.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

;

public class SaleControllerTest extends RestdocsPresentaionTestContext {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private SaleItemRepository saleItemRepository;

    @After
    public void tearDown() throws Exception {
        saleItemRepository.deleteAllInBatch();
    }

    @Test
    public void 데이터_노출() {
        //given
        saleItemRepository.save(
                SaleItem.builder()
                .price("500원")
                .title("맥북")
                .postId("1")
                .link("kingbbode.com")
                .site(Site.ALGUMON)
                .build()
        );

        //when
        MockMvcResponse response = given()
                .param("date", LocalDate.now().format(format))
                .get("/sales");

        //then
        response.prettyPrint();
        response.then()
                .apply(
                        document("get-sales",
                                preprocessTheftRequest(),
                                preprocessTheftResponse(),
                                requestParameters(
                                        parameterWithName("date").description("날짜")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).attributes(emptyFormat()).description("협의 응답 코드").ignored(),
                                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(emptyFormat()).description("협의 응답 메시지").ignored(),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).attributes(emptyFormat()).description("협약 구성 DATA").ignored(),
                                        fieldWithPath("data.algumon").type(JsonFieldType.ARRAY).attributes(emptyFormat()).description(""),
                                        fieldWithPath("data.algumon[].createdDate").type(JsonFieldType.STRING).attributes(emptyFormat()).description(""),
                                        fieldWithPath("data.algumon[].modifiedDate").type(JsonFieldType.STRING).attributes(emptyFormat()).description(""),
                                        fieldWithPath("data.algumon[].id").type(JsonFieldType.NUMBER).attributes(emptyFormat()).description(""),
                                        fieldWithPath("data.algumon[].title").type(JsonFieldType.STRING).attributes(emptyFormat()).description(""),
                                        fieldWithPath("data.algumon[].site").type(JsonFieldType.STRING).attributes(enumFormat(Site.class)).description(""),
                                        fieldWithPath("data.algumon[].postId").type(JsonFieldType.STRING).attributes(emptyFormat()).description(""),
                                        fieldWithPath("data.algumon[].link").type(JsonFieldType.STRING).attributes(emptyFormat()).description(""),
                                        fieldWithPath("data.algumon[].price").type(JsonFieldType.STRING).attributes(emptyFormat()).description("")
                                )
                        )
                ).statusCode(200);

    }

    @Test
    public void 데이터_갱신() {
        //given
        when(algumonClient.parse()).thenReturn(Arrays.asList(
                AlgumonSaleItem.builder()
                    .price("500원")
                    .title("맥북")
                    .postId("1")
                    .link("kingbbode.com")
                    .build()
        ));

        //when
        MockMvcResponse response = given()
                .post("/sales");

        //then
        response.prettyPrint();
        response.then()
                .apply(
                        document("post-sales",
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).attributes(emptyFormat()).description("협의 응답 코드").ignored(),
                                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(emptyFormat()).description("협의 응답 메시지").ignored(),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).attributes(emptyFormat()).description("협약 구성 DATA").ignored(),
                                        fieldWithPath("data.count").type(JsonFieldType.NUMBER).attributes(emptyFormat()).description("")
                                )
                        )
                ).statusCode(200);

        assertEquals(1, saleItemRepository.findAll().size());
    }


}
