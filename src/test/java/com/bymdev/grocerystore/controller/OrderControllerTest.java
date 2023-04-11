package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private ElasticsearchOperations elasticsearchOperations;

    @InjectMocks
    private OrderController orderController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getOrders method")
    void testGetOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder().id(1).title("Order 1").build());
        orders.add(Order.builder().id(2).title("Order 2").build());
        orders.add(Order.builder().id(3).title("Order 3").build());
        Page<Order> page = new PageImpl<>(orders);

        when(orderService.getAllOrders(Pageable.unpaged())).thenReturn(page);

        Page<Order> result = orderController.getOrders(Pageable.unpaged());

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(3);
    }

    @Test
    @DisplayName("Test getOrder method")
    void testGetOrder() {
        int orderId = 1;
        Order order = Order.builder().id(orderId).title("Order 1").build();

        when(orderService.getOrderById(orderId)).thenReturn(order);

        Order result = orderController.getOrder(orderId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(orderId);
        assertThat(result.getTitle()).isEqualTo("Order 1");
    }

    @Test
    @DisplayName("Test addOrder method")
    void testAddOrder() {
        Order order = Order.builder().id(null).title("New Order").build();

        Order savedOrder = Order.builder().id(1).title("New Order").build();
        when(orderService.addOrder(order)).thenReturn(savedOrder);

        when(elasticsearchOperations.save(savedOrder)).thenReturn(savedOrder);

        Order result = orderController.addOrder(order);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getTitle()).isEqualTo("New Order");
    }

    @Test
    @DisplayName("Test updateOrder method")
    void testUpdateOrder() {
        int orderId = 1;
        Order order = Order.builder().id(orderId).title("Updated Order").build();

        when(orderService.updateOrder(orderId, order)).thenReturn(order);

        Order result = orderController.updateOrder(orderId, order);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(orderId);
        assertThat(result.getTitle()).isEqualTo("Updated Order");
    }

    @Test
    @DisplayName("Test deleteOrder method")
    void testDeleteOrder() {
        int orderId = 1;
        orderController.deleteOrder(orderId);
        verify(orderService).deleteOrderById(orderId);
    }

}
