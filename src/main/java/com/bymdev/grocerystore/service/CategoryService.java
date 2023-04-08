package com.bymdev.grocerystore.service;

import com.bymdev.grocerystore.domain.Category;
import com.bymdev.grocerystore.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(Pageable page) {
        return categoryRepository.findAll(page);
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("There is no such Entity"));
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(UUID id, Category category) {
        Category retrivedCategory = categoryRepository.getReferenceById(id);
        retrivedCategory.setName(category.getName());
        retrivedCategory.setProducts(category.getProducts());
        return categoryRepository.save(retrivedCategory);
    }

    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);
    }
}
