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
    public PostServiceImpl(PostRepository repository) {
        this.repository=repository;
    }


    @Override
    public Post createPost(UUID id, String name, String description) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Post name cannot be empty");
        }
        Post post = new Post(name, id != null ? id : UUID.randomUUID());
        post.setDescription(description);
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
    public Post updatePost(UUID id, String name) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Post name cannot be empty");
        }
        Post post = getPostById(id);
        post.setName(name);
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
