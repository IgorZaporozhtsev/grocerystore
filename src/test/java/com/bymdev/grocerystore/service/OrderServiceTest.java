package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.domain.OrderItem;
import com.bymdev.grocerystore.domain.Product;
import com.bymdev.grocerystore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;
    private Order order;

    @BeforeEach
    public void initEach() {
        orderService = new OrderService(orderRepository);

        var coffee = Product.builder()
                .id(1)
                .price(BigDecimal.valueOf(20))
                .sku("432514")
                .productName("Coffee")
                .build();

        var item = OrderItem.builder()
                .id(1)
                .quantity(3)
                .product(coffee)
                .build();

        order = Order.builder()
                .id(1)
                .title("title of Order")
                .totalAmount(BigDecimal.valueOf(160))
                .orderItems(List.of(item))
                .orderDate(LocalDate.now())
                .build();
    }


    @Test
    void shouldReturnOrderWhenExists() {
        given(orderRepository.findById(1)).willReturn(Optional.of(order));
        Order actualOrder = orderService.getOrderById(1);
        assertEquals(order, actualOrder);
    }

}