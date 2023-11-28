package com.example.electronicshop.service;

import com.example.electronicshop.model.Brand;
import com.example.electronicshop.repo.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandImpl implements BrandService {
    @Autowired
    BrandRepository repo;

    @Override
    public List<Brand> getBrands() {
        List<Brand> brands = new ArrayList<>();
        repo.findAll().forEach(brands::add);
        return brands;
    }

    @Override
    public Brand getBrandById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Brand insert(Brand brand) {
        return repo.save(brand);
    }

    @Override
    public void updateBrand(Long id, Brand brand) {
        Brand brandFromDb = repo.findById(id).get();
        System.out.println(brandFromDb.toString());
        brandFromDb.setName(brand.getName());
        repo.save(brandFromDb);
    }

    @Override
    public void deleteBrand(Long Id) {
        repo.deleteById(Id);
    }
}
