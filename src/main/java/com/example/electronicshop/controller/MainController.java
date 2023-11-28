package com.example.electronicshop.controller;

import com.example.electronicshop.model.Brand;
import com.example.electronicshop.model.Product;
import com.example.electronicshop.service.BrandService;
import com.example.electronicshop.service.CategoryService;
import com.example.electronicshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;


    @GetMapping(value="/")
    public String index(Model model){
        List<Product> drivers = productService.getProducts();
        model.addAttribute("product", drivers);
        model.addAttribute("category", categoryService.getCategories());
        model.addAttribute("brand", brandService.getBrands());
        return "index";
    }




}
