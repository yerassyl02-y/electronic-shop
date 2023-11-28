package com.example.electronicshop.service;

import com.example.electronicshop.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrands();

    Brand getBrandById(Long id);

    Brand insert(Brand brand);

    void updateBrand(Long id, Brand brand);

    void deleteBrand(Long Id);
}
