package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportController {

    private final OrderService orderService;

    //2016-08-22: 250.65,
    @GetMapping("/statistic")
    @ResponseStatus(code = HttpStatus.OK)
    public Map<LocalDate, BigDecimal> getDayStatistic() {
        return orderService.processDallyIncome();
    }

}
