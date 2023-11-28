package com.example.electronicshop.service;

import com.example.electronicshop.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    Category getCategoryById(Long id);

    Category insert(Category category);

    void updateCategory(Long id, Category category);

    void deleteCategory(Long Id);
}
