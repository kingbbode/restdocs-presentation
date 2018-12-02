package com.github.kingbbode.restdocs.config;

import com.github.kingbbode.algumon.AlgumonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgumonClientConfig {

    @Bean
    public AlgumonClient algumonClient() {
        return new AlgumonClient();
    }
}
