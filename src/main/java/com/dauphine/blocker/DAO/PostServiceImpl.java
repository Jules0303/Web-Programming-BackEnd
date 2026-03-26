package com.dauphine.blocker.DAO;

import com.dauphine.blocker.exception.BadRequestException;
import com.dauphine.blocker.exception.PostNotFoundException;
import com.dauphine.blocker.BlockerBoxBackendApplication;
import com.dauphine.blocker.Model.Post;
import com.dauphine.blocker.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements BlockerBoxBackendApplication.PostService {


    private final PostRepository repository;
    private final com.dauphine.blocker.Repository.CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository repository, com.dauphine.blocker.Repository.CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Post createPost(UUID id, String name, String description, UUID categoryId) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Post name cannot be empty");
        }
        Post post = new Post(name, id != null ? id : UUID.randomUUID());
        post.setDescription(description);
        
        if (categoryId != null) {
            com.dauphine.blocker.Model.Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new BadRequestException("Category with id " + categoryId + " not found"));
            post.setCategory(category);
        }
        
        return repository.save(post);
    }

    @Override
    public Post getPostById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new PostNotFoundException("Post with id " + id + " not found"));
    }

    @Override
    public void deletePost(UUID id) {
        Post post = getPostById(id);
        repository.delete(post);
    }

    @Override
    public Post updatePost(UUID id, String name, String description, UUID categoryId) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Post name cannot be empty");
        }
        Post post = getPostById(id);
        post.setName(name);
        
        if (description != null) {
            post.setDescription(description);
        }
        
        if (categoryId != null) {
            com.dauphine.blocker.Model.Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new BadRequestException("Category with id " + categoryId + " not found"));
            post.setCategory(category);
        }
        
        return repository.save(post);
    }

    @Override
    public void deleteAllPosts() {

        repository.deleteAll();
    }

    @Override
    public List<Post> GetAll() {
       return repository.findAll();
    }

    @Override
    public List<Post> GetAllbyCategory(UUID CategoryID) {
        return repository.findAllByCategory_Id(CategoryID);
    }
}
