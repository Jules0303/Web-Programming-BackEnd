package com.dauphine.blocker.controller;

import com.dauphine.blocker.BlockerBoxBackendApplication;
import com.dauphine.blocker.Model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final BlockerBoxBackendApplication.PostService postService;
    public PostController(BlockerBoxBackendApplication.PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> CreatePost(@RequestBody Post post){
        Post createdPost = postService.createPost(post.getId(), post.getName(), post.getDescription());
        return ResponseEntity.status(201).body(createdPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> GetPostbyId(@PathVariable UUID id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletePost(@PathVariable UUID id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> UpdatePost(@PathVariable UUID id, @RequestBody Post postRequest){
        return ResponseEntity.ok(postService.updatePost(id, postRequest.getName()));
    }

    @DeleteMapping
    public ResponseEntity<Void> DeleteAllPost(){
        postService.deleteAllPosts();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> GetAllPost(){
        return ResponseEntity.ok(postService.GetAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Post>> GetAllPostbyCategory(@PathVariable UUID id){
        return ResponseEntity.ok(postService.GetAllbyCategory(id));
    }

}
