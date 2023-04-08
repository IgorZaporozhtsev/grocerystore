package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<Order> getOrders(@PageableDefault(sort = "orderId", size = 5) Pageable page) {
        return orderService.getAllOrders(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Order getOrder(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Order updateOrder(@PathVariable UUID id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
    }
}
