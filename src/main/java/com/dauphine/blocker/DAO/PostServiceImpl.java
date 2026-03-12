package com.dauphine.blocker.DAO;

import com.dauphine.blocker.BlockerBoxBackendApplication;
import com.dauphine.blocker.Model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements BlockerBoxBackendApplication.PostService {


    private final List<Post> posts;
    public PostServiceImpl() {
        this.posts = new ArrayList<>();
        posts.add( new Post("Post 1", UUID.randomUUID()));
        posts.add( new Post("Post 2", UUID.randomUUID()));
        posts.add( new Post("Post 3", UUID.randomUUID()));
    }


    @Override
    public Post createPost(UUID id, String name, String description) {
        Post post = new Post(name, id);
        post.setDescription(description);
        posts.add(post);
        return post;
    }

    @Override
    public Post getPostById(UUID id) {
        return posts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deletePost(UUID id) {
        posts.removeIf(post -> post.getId().equals(id));
    }

    @Override
    public Post updatePost(UUID id, String name) {
        posts.stream().filter(post -> post.getId().equals(id)).findFirst().ifPresent(post -> post.setName(name));
        return getPostById(id);
    }

    @Override
    public void deleteAllPosts() {
        posts.clear();
    }

    @Override
    public List<Post> GetAll() {
       return posts;
    }

    @Override
    public List<Post> GetAllbyCategory(UUID CategoryID) {
        return Collections.singletonList(posts.stream().filter(post -> post.getCategoryId().equals(CategoryID)).findFirst().orElse(null));
    }
}
