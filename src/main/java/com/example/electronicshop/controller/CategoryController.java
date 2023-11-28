package com.example.electronicshop.controller;

import com.example.electronicshop.model.Category;
import com.example.electronicshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping(value="/create")
    public String create(){
        return "category/create";
    }

    @PostMapping(value="/add")
    public String addItem(@RequestParam(name ="name", defaultValue = "No name") String name){

        Category category = new Category(null, name);
        service.insert(category);

        return "redirect:/";
    }

    @GetMapping({"/details/{id}"})
    public String details(Model model, @PathVariable Long id){
        Category category = service.getCategoryById(id);
        model.addAttribute("category_to_edit", category);
        return "category/detail";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(Model model, @PathVariable Long id){
        Category category = service.getCategoryById(id);
        model.addAttribute("category_to_edit", category);
        return "category/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") long id, @RequestParam(name ="id", defaultValue = "0") Long idi,
                         @RequestParam(name ="name", defaultValue = "No name") String name) {
        Category category = new Category(idi, name);
        service.updateCategory(id, category);
        return "redirect:/";
    }

    @GetMapping(value="/delete/{id}")
    public String delete(@PathVariable Long id){
        service.deleteCategory(id);
        return "redirect:/";
    }
}
