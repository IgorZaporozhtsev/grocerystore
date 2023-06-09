package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.domain.OrderItem;
import com.bymdev.grocerystore.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Page<Order> getAllOrders(Pageable page) {
        return orderRepository.findAll(page);
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("There is no such Entity"));
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
    public Order updateOrder(Integer id, Order order) {
        BigDecimal totalPrice = calculateOrderPrice(order);
        Order retrivedOrder = getOrderById(id);
        retrivedOrder.setTotalAmount(totalPrice);
        retrivedOrder.addOrderItems(order.getOrderItems());
        return orderRepository.save(retrivedOrder);
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    public Map<LocalDate, BigDecimal> processDallyIncome() {
        Iterable<Order> orders = orderRepository.findByOrderDate(LocalDate.now());

        return StreamSupport.stream(orders.spliterator(), false)
                .collect(Collectors.groupingBy(
                Order::getOrderDate,
                Collectors.mapping(
                        Order::getTotalAmount,
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
        ));
    }


}
