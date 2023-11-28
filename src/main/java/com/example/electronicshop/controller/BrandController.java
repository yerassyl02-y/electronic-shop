package com.example.electronicshop.controller;

import com.example.electronicshop.model.Brand;
import com.example.electronicshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    BrandService service;

    @GetMapping(value="/create")
    public String create(){
        return "brand/create";
    }

    @PostMapping(value="/add")
    public String addItem(@RequestParam(name ="name", defaultValue = "No name") String name){

        Brand brand = new Brand(null, name);
        service.insert(brand);

        return "redirect:/";
    }

    @GetMapping({"/details/{id}"})
    public String details(Model model, @PathVariable Long id){
        Brand brand = service.getBrandById(id);
        model.addAttribute("brand_to_edit", brand);
        return "brand/detail";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        Brand brand = service.getBrandById(id);
        model.addAttribute("brand_to_edit", brand);
        return "brand/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") long id, @RequestParam(name ="id", defaultValue = "0") Long idi,
                         @RequestParam(name ="name", defaultValue = "No name") String name) {
        Brand brand = new Brand(idi, name);
        service.updateBrand(id, brand);
        return "redirect:/";
    }

    @GetMapping(value="/delete/{id}")
    public String delete(@PathVariable Long id){
        service.deleteBrand(id);
        return "redirect:/";
    }
}
