package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.OrderItem;
import com.bymdev.grocerystore.repository.OrderItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemItemRepository;

    public Page<OrderItem> getAllOrderItems(Pageable page) {
        return orderItemItemRepository.findAll(page);
    }

    public com.bymdev.grocerystore.domain.OrderItem getOrderItemById(UUID id) {
        return orderItemItemRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("There is no such Entity"));
    }

    public OrderItem addOrderItem(OrderItem orderItemItem) {
        return orderItemItemRepository.save(orderItemItem);
    }

    @Transactional
    public OrderItem updateOrderItem(UUID id, OrderItem orderItem) {
        OrderItem retrivedOrderItem = getOrderItemById(id);
        retrivedOrderItem.setProduct(orderItem.getProduct());
        retrivedOrderItem.setQuantity(orderItem.getQuantity());
        return orderItemItemRepository.save(retrivedOrderItem);
    }

    public void deleteOrderItemById(UUID id) {
        orderItemItemRepository.deleteById(id);
    }
}
