package com.github.kingbbode.restdocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RestdcosPresentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestdcosPresentationApplication.class, args);
    }
}
