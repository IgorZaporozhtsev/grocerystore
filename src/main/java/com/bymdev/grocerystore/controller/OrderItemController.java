package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.domain.OrderItem;
import com.bymdev.grocerystore.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<OrderItem> getOrderItems(@PageableDefault(sort = "orderItemId", size = 5) Pageable page) {
        return orderItemService.getAllOrderItems(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public OrderItem getOrderItem(@PathVariable UUID id) {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.addOrderItem(orderItem);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public OrderItem updateOrderItem(@PathVariable UUID id, @RequestBody OrderItem orderItem) {
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@PathVariable UUID id) {
        orderItemService.deleteOrderItemById(id);
    }
}
