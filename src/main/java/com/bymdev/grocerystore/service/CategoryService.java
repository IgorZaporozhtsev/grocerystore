package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.Category;
import com.bymdev.grocerystore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return null;
    }

    public Category getCategoryById(UUID id) {
        return null;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(UUID id, Category category) {
        return null;
    }

    public void deleteCategoryById(UUID id) {

    }
}
