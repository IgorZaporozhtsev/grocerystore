package com.bymdev.grocerystore.controller;

import com.bymdev.grocerystore.domain.Order;
import com.bymdev.grocerystore.domain.OrderItem;
import com.bymdev.grocerystore.domain.Product;
import com.bymdev.grocerystore.repository.OrderRepository;
import com.bymdev.grocerystore.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
class OrderControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService service;
    @MockBean
    private OrderRepository orderRepository;
    @MockBean
    private ElasticsearchOperations elasticsearchOperations;

    private Order order;

    @BeforeEach
    public void initEach() {

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

    //1. Verifying HTTP Request Matching
    @Test
    void should_return_status_200_getOrders() throws Exception {
        var orderPage = new PageImpl<>(List.of(order));

        given(service.getAllOrders(any(Pageable.class))).willReturn(orderPage);

        mockMvc.perform(get("/api/orders")
                        .contentType("application/json"))
                .andDo(print())
                //this is one approach
                .andExpect(content().string(objectMapper.writeValueAsString(orderPage)))
                // this approach with json path
                .andExpect(jsonPath("$.content.[0].id").value(order.getId()))
                .andExpect(status().isOk());

    }

    @Test
    void should_return_status_200_getOrder() throws Exception {


        given(service.getOrderById(any(Integer.class))).willReturn(order);

        mockMvc.perform(get("/api/orders/{id}", 1)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(order.getId().toString()))
                .andExpect(status().isOk());
    }

    //2. Verifying Input Deserialization
    @Test
    void should_return_status_201_addOrder() throws Exception {

        given(service.addOrder(any(Order.class))).willReturn(order);

        mockMvc.perform(post("/api/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(order))
                )
                .andDo(print())
                .andExpect(content().string(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated());
    }

    //3.Matching Expected Validation Errors

    @Test
    void should_return_valid_error_message_when_items_are_empty() throws Exception {

        var emptyOrder = Order.builder()
                .id(1)
                .title("title of Order")
                .totalAmount(BigDecimal.valueOf(160))
                .orderItems(List.of())
                .orderDate(LocalDate.now())
                .build();

        mockMvc.perform(post("/api/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(emptyOrder))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage.orderItems").value("must not be empty"));
    }


}