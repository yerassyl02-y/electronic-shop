package com.example.electronicshop.controller;

import com.example.electronicshop.model.Brand;
import com.example.electronicshop.model.Category;
import com.example.electronicshop.model.Product;
import com.example.electronicshop.service.BrandService;
import com.example.electronicshop.service.CategoryService;
import com.example.electronicshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService service;
    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;

    @GetMapping(value="/search")
    public String searchPage(){
        return "product/search";
    }

    @PostMapping(value="/search")
    public String search(Model model, @RequestParam(name="name") String name){
        List<Product> products_from_search = service.searchProduct(name.toLowerCase());
        model.addAttribute("products_from_search", products_from_search);
        return "product/search";
    }

    @GetMapping(value="/filter")
    public String filt(Model model){
        model.addAttribute("category", categoryService.getCategories());
        model.addAttribute("brand", brandService.getBrands());

        return "product/filter";
    }
    @PostMapping(value="/filter")
    public String filter(Model model,
                         @RequestParam(name="category") Category category,
                         @RequestParam(name="brand") Brand brand,
                         @RequestParam(name="active", defaultValue = "false")boolean active,
                         @RequestParam(name = "priceFirstRange", defaultValue = "0")int priceFirstRange,
                         @RequestParam(name = "priceSecondRange", defaultValue = "100000")int priceSecondRange
                         ){
        List<Product> product_from_filter = service.filterProduct(category, brand, active, priceFirstRange, priceSecondRange);
        model.addAttribute("product_from_filter", product_from_filter);
        return "product/filter";

    }


    @GetMapping(value="/create")
    public String create(Model model){
        model.addAttribute("category", categoryService.getCategories());
        model.addAttribute("brand", brandService.getBrands());

        return "product/create";
    }

    @PostMapping(value="/add")
    public String addItem(@RequestParam(name ="name", defaultValue = "No name") String name,
                          @RequestParam(name = "price", defaultValue = "0") int price,
                          @RequestParam(name = "desc", defaultValue = "0") String desc,
                          @RequestParam(name = "active", defaultValue = "true") boolean isActive,
                          @RequestParam(name = "category", defaultValue = "0") Category category,
                          @RequestParam(name = "brand", defaultValue = "0") Brand brand){

        Product product = new Product(null, name, price, desc, isActive, brand,category);
        service.insert(product);

        return "redirect:/";
    }

    @GetMapping({"/details/{id}"})
    public String details(Model model, @PathVariable Long id){
        Product product = service.getProductById(id);
        model.addAttribute("product_to_edit", product);
        return "product/detail";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(ModelMap model, @PathVariable Long id){
        Product product = service.getProductById(id);
        model.addAttribute("product_to_edit", product);
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("brands", brandService.getBrands());
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("product_to_edit") Product product) {
        product.setId(id);
        service.updateProduct(id, product);
        return "redirect:/";
    }

    @GetMapping(value="/delete/{id}")
    public String delete(@PathVariable Long id){
        service.deleteProduct(id);
        return "redirect:/";
    }
}
