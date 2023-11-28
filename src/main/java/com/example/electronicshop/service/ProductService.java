package com.example.electronicshop.service;

import com.example.electronicshop.model.Brand;
import com.example.electronicshop.model.Category;
import com.example.electronicshop.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    List<Product> searchProduct(String search);

    List<Product> filterProduct(Category category, Brand brand, boolean active, int priceFirstRange, int priceSecondRage);

    Product getProductById(Long id);

    Product insert(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long Id);

}
