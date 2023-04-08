package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Page<Order> getAllOrders(Pageable page) {
        return orderRepository.findAll(page);
    }

    public com.bymdev.grocerystore.domain.Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("There is no such Entity"));
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(UUID id, Order order) {
        Order retrivedOrder = orderRepository.getReferenceById(id);
        retrivedOrder.setTotalAmount(order.getTotalAmount());
        retrivedOrder.setOrderItems(order.getOrderItems());
        return orderRepository.save(retrivedOrder);
    }

    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }
}
