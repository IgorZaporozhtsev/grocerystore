package com.bymdev.grocerystore.repository;

import com.bymdev.grocerystore.domain.Category;
import com.bymdev.grocerystore.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
