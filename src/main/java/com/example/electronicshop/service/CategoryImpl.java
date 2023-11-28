package com.example.electronicshop.service;

import com.example.electronicshop.model.Category;
import com.example.electronicshop.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    CategoryRepository repo;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        repo.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Category insert(Category category) {
        return repo.save(category);
    }

    @Override
    public void updateCategory(Long id, Category category) {
        Category categoryFromDb = repo.findById(id).get();
        System.out.println(categoryFromDb.toString());
        categoryFromDb.setName(category.getName());
        repo.save(categoryFromDb);
    }

    @Override
    public void deleteCategory(Long Id) {
        repo.deleteById(Id);
    }
}
