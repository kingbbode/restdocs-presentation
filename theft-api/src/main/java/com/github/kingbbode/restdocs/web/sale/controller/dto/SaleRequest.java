package com.github.kingbbode.restdocs.web.sale.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SaleRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
