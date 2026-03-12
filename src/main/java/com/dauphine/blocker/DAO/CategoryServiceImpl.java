package com.dauphine.blocker.DAO;

import com.dauphine.blocker.Repository.CategoryRepository;
import com.dauphine.blocker.Service.CategoryService;
import com.dauphine.blocker.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;


    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(String name) {
       Category category = new Category(UUID.randomUUID(),name);
       return repository.save(category);
    }

    @Override
    public Category updateCategory(UUID id, String name) {
        Category category = getCategoryById(id);
        if(category == null){return null;}
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
     repository.deleteById(id);
    }
}