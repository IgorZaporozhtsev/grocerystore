package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.domain.OrderItem;
import com.bymdev.grocerystore.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<OrderItem> getOrderItems(@PageableDefault(sort = "id", size = 5) Pageable page) {
        return orderItemService.getAllOrderItems(page);
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public OrderItem getOrderItem(@PathVariable Integer id) {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderItem addOrderItem(@RequestBody @Valid OrderItem orderItem) {
        return orderItemService.addOrderItem(orderItem);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public OrderItem updateOrderItem(@PathVariable Integer id, @RequestBody @Valid OrderItem orderItem) {
        return orderItemService.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteOrderItemById(id);
    }
}
