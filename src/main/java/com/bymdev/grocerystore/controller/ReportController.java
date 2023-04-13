package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.elasticsearch.OrderElasticSearchRepository;
import com.bymdev.grocerystore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistic")
public class ReportController {

    private final OrderService orderService;
    private final OrderElasticSearchRepository orderElasticSearchRepository;


    //2016-08-22: 250.65,
    @GetMapping("/daily")
    @ResponseStatus(code = HttpStatus.OK)
    public Map<LocalDate, BigDecimal> getDayStatistic() {
        return orderService.processDallyIncome();
    }

    @GetMapping(value = "product/{name}", produces = "application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Order> productNameSearch(@PathVariable String name) {
        return orderElasticSearchRepository.findByProductName(name);
    }


}
