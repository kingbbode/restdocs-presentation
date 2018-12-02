package com.github.kingbbode.restdocs;


import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class RestdocsPresentaionTestContext {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");
    
    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc createMockMvc() {
        return MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    protected MockMvcRequestSpecification given() {
        MockMvc mockMvc = createMockMvc();
        return given(mockMvc);
    }

    protected MockMvcRequestSpecification given(MockMvc mockMvc) {
        return io.restassured.module.mockmvc.RestAssuredMockMvc.given()
                .mockMvc(mockMvc);
    }
}

