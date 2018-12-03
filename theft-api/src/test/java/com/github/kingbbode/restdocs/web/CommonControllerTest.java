package com.github.kingbbode.restdocs.web;

import com.github.kingbbode.restdocs.RestdocsPresentaionTestContext;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.github.kingbbode.restdocs.RestDocsUtils.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class CommonControllerTest extends RestdocsPresentaionTestContext {

    private MockMvcRequestSpecification given;

    @Before
    public void setUp() throws Exception {
        given = given(MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(new TestController.TestControllerAdvice())
                .apply(documentationConfiguration(restDocumentation))
                .build());
    }

    @Test
    public void 성공() {
        //when
        MockMvcResponse response = given
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/success");

        //then
        response.prettyPrint();
        response.then()
                .apply(
                        document("success",
                                preprocessTheftRequest(),
                                preprocessTheftResponse(),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).attributes(emptyFormat()).description("응답 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(emptyFormat()).description("응답 메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).attributes(emptyFormat()).description("협약 구성 DATA"),
                                        fieldWithPath("data.data").type(JsonFieldType.STRING).attributes(emptyFormat()).description("example")
                                )
                        )
                )
                .statusCode(200);
    }

    @Test
    public void 실패() {
        //when
        MockMvcResponse response = given
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/fail");

        //then
        response.prettyPrint();
        response.then()
                .apply(
                        document("fail",
                                preprocessTheftRequest(),
                                preprocessTheftResponse(),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).attributes(emptyFormat()).description("협의 응답 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(emptyFormat()).description("협의 응답 메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).attributes(emptyFormat()).description("협약 구성 DATA"),
                                        fieldWithPath("data.data").type(JsonFieldType.STRING).attributes(emptyFormat()).description("example")
                                )
                        )
                )
                .statusCode(200);
    }

    @Test
    public void 에러_4xx() {
        //when
        MockMvcResponse response = given
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/bad-request");

        //then
        response.prettyPrint();
        response.then()
                .apply(
                        document("bad-request",
                                preprocessTheftRequest(),
                                preprocessTheftResponse(),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).attributes(emptyFormat()).description("응답 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(emptyFormat()).description("에러 메시지"),
                                        fieldWithPath("data").type(JsonFieldType.NULL).attributes(emptyFormat()).description("")
                                )
                        )
                )
                .statusCode(400);
    }

    @Test
    public void 에러_5xx() {
        //when
        MockMvcResponse response = given
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("/internal-server-error");

        //then
        response.prettyPrint();
        response.then()
                .apply(
                        document("internal-server-error",
                                preprocessTheftRequest(),
                                preprocessTheftResponse(),
                                responseFields(
                                        fieldWithPath("status").type(JsonFieldType.STRING).attributes(emptyFormat()).description("응답 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).attributes(emptyFormat()).description("에러 메시지"),
                                        fieldWithPath("data").type(JsonFieldType.NULL).attributes(emptyFormat()).description("")
                                )
                        )
                )
                .statusCode(500);
    }


}
