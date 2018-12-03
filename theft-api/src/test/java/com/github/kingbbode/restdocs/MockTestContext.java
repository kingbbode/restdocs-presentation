package com.github.kingbbode.restdocs;

import com.github.kingbbode.algumon.AlgumonClient;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class MockTestContext {

    @MockBean
    protected AlgumonClient algumonClient;
}
