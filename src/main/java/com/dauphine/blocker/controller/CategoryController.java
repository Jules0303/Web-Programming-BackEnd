package com.dauphine.blocker.controller;


import com.dauphine.blocker.Model.Category;
import com.dauphine.blocker.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id)
    {
        Category cat = categoryService.getCategoryById(id);
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category categoryRequest){
        Category category = categoryService.createCategory(categoryRequest.getName());
        return ResponseEntity.status(201).body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody Category categoryRequest){
        Category category = categoryService.updateCategory(id, categoryRequest.getName());
        return ResponseEntity.ok(category);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Category>> FindAllLikeName(@PathVariable String name){
        return ResponseEntity.ok(categoryService.FindAllLikeName(name));
    }


}
