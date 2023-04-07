package com.bymdev.grocerystore.repository;

import com.bymdev.grocerystore.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
