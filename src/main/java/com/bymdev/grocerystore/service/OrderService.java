package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.domain.OrderItem;
import com.bymdev.grocerystore.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional
    public Order addOrder(Order order) {
        BigDecimal totalPrice = calculateOrderPrice(order);
        order.setTotalAmount(totalPrice);
        return orderRepository.save(order);
    }

    private BigDecimal calculateOrderPrice(Order order){
        return order.getOrderItems().stream()
                .map(sumUpAllItemsPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Function<OrderItem, BigDecimal> sumUpAllItemsPrice() {
        return orderItem -> {
            int quantity = orderItem.getQuantity();
            BigDecimal price = orderItem.getProduct().getPrice();
            return price.multiply(BigDecimal.valueOf(quantity));
        };
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

    public Map<LocalDate, BigDecimal> processDallyIncome() {
        return orderRepository.findAll().stream().collect(Collectors.groupingBy(
                Order::getOrderDate,
                Collectors.mapping(
                        Order::getTotalAmount,
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
        ));
    }



}
