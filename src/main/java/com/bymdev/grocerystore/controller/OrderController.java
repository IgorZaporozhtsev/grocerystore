package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.service.OrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ElasticsearchOperations elasticsearchOperations;


/*    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<Order> getOrders(@PageableDefault(sort = "orderId", size = 5) Pageable page) {
        return orderService.getAllOrders(page);
    }*/

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order order) {
        var saved = orderService.addOrder(order);
        Objects.requireNonNull(saved.getId());
        var savedEl = elasticsearchOperations.save(saved);
        log.info("Order was saved to elasticsearch " + savedEl);
        return saved;
    }

//    @PutMapping("/{id}")
//    @ResponseStatus(code = HttpStatus.OK)
//    public Order updateOrder(@PathVariable UUID id, @RequestBody Order order) {
//        return orderService.updateOrder(id, order);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }
}
