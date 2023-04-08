package com.bymdev.grocerystore.repository;

import com.bymdev.grocerystore.domain.Category;
import com.bymdev.grocerystore.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
