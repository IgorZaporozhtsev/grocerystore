package com.bymdev.grocerystore.repository;

import com.bymdev.grocerystore.domain.Category;
import com.bymdev.grocerystore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
