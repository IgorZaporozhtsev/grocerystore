package com.bymdev.grocerystore.repository;

import com.bymdev.grocerystore.domain.Category;
import com.bymdev.grocerystore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    //List<Order> getOrderByOrderDate(LocalDate date);
}
