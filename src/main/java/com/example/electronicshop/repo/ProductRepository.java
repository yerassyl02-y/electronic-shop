package com.example.electronicshop.repo;

import com.example.electronicshop.model.Brand;
import com.example.electronicshop.model.Product;
import com.example.electronicshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT product from Products product WHERE LOWER(product.name) LIKE %?1%")
    public List<Product> findAll(String search);
    @Query("SELECT product from Products product WHERE product.category = ?1 AND product.brand = ?2  AND product.isActive = ?3 AND product.price >= ?4 AND product.price <=?5")
    public List<Product> findAll(Category category, Brand brand, Boolean active, int priceFirstRange, int priceSecondRange);

}
