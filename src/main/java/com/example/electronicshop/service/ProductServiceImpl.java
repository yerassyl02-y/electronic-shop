package com.example.electronicshop.service;

import com.example.electronicshop.model.Brand;
import com.example.electronicshop.model.Category;
import com.example.electronicshop.model.Product;
import com.example.electronicshop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repo;

    @Override
    public List<Product> getProducts() {
        List<Product> drivers = new ArrayList<>();
        repo.findAll().forEach(drivers::add);
        return drivers;
    }

    @Override
    public List<Product> searchProduct(String search) {
        if(search!=null){
            return repo.findAll(search.toLowerCase());

        }
        return (List<Product>) repo.findAll();
    }

    @Override
    public List<Product> filterProduct(Category category, Brand brand, boolean active, int priceFirstRange, int priceSecondRange) {
        System.out.print(category+ " " +brand+ " " +active + " " + Boolean.valueOf(active? "true": "false") + " " +priceFirstRange+ " " +priceSecondRange);
        return repo.findAll(category, brand, Boolean.valueOf(active? "true": "false"), priceFirstRange, priceSecondRange);
    }

    @Override
    public Product getProductById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Product insert(Product product) {
            return repo.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        repo.save(product);
    }

    @Override
    public void deleteProduct(Long Id) {
            repo.deleteById(Id);
    }
}
