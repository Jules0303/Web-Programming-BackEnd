package com.dauphine.blocker.controller;


import com.dauphine.blocker.Model.Category;
import com.dauphine.blocker.Service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categorie")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable UUID id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/create")
    public Category createCategory(String name){
        return categoryService.createCategory(name);
    }

    @GetMapping("/delete/{id}")
    public void deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
    }

    @GetMapping("/update/{id}")
    public Category updateCategory(@PathVariable UUID id, String name){
        return categoryService.updateCategory(id,name);
    }

    @GetMapping("/{name}")
    public List<Category> FindAllLikeName(@PathVariable String name){
        return categoryService.FindAllLikeName(name);
    }


}
