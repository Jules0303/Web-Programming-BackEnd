package com.dauphine.blocker.DAO;

import com.dauphine.blocker.exception.BadRequestException;
import com.dauphine.blocker.exception.CategoryNotFoundException;
import com.dauphine.blocker.Repository.CategoryRepository;
import com.dauphine.blocker.Service.CategoryService;
import com.dauphine.blocker.Model.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
    }

    @Override
    public Category createCategory(String name) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Category name cannot be empty");
        }
        Category category = new Category(UUID.randomUUID(), name);
        return repository.save(category);
    }

    @Override
    public Category updateCategory(UUID id, String name) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Category name cannot be empty");
        }
        Category category = getCategoryById(id);
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Category category = getCategoryById(id);
        repository.delete(category);
    }

    @Override
    public List<Category> FindAllLikeName(String name) {
        return repository.FindAllLikeName(name);
    }


}