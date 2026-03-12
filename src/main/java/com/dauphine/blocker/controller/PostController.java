package com.dauphine.blocker.controller;

import com.dauphine.blocker.BlockerBoxBackendApplication;
import com.dauphine.blocker.Model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final BlockerBoxBackendApplication.PostService postService;
    public PostController(BlockerBoxBackendApplication.PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public Post CreatePost(Post post){
        return postService.createPost(post.getId(),post.getName(),post.getDescription());
    }

    @GetMapping("/{id}")
    public Post GetPostbyId(UUID id){
        return postService.getPostById(id);
    }

    @GetMapping("/delete/{id}")
    public void DeletePost(UUID id){
        postService.deletePost(id);
    }

    @GetMapping("/update/{id}")
    public Post UpdatePost(UUID id, String name){
        return postService.updatePost(id,name);
    }

    @GetMapping("/deleteAll")
    public void DeleteAllPost(){
        postService.deleteAllPosts();
    }

    @GetMapping("/getAll")
    public List<Post> GetAllPost(){
        return postService.GetAll();
    }

    @GetMapping("/getAllbyCategory/{id}")
    public List<Post> GetAllPostbyCategory(UUID id){
        return postService.GetAllbyCategory(id);
    }

}
