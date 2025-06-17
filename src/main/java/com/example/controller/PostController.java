package com.example.controller;


import com.example.dto.PostRequestDTO;
import com.example.dto.PostResponseDTO;
import com.example.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

	@Autowired
    private  PostService postService;

   

    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO request,
                                                   @RequestHeader("X-User") String createdBy) {
        return ResponseEntity.ok(postService.createPost(request, createdBy));
    }

    @GetMapping("/published")
    public ResponseEntity<List<PostResponseDTO>> getPublishedPosts() {
        return ResponseEntity.ok(postService.getAllPublishedPosts());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<PostResponseDTO>> getPendingPosts() {
        return ResponseEntity.ok(postService.getPendingPosts());
    }

    @GetMapping("/user")
    public ResponseEntity<List<PostResponseDTO>> getUserPosts(@RequestHeader("X-User") String username) {
        return ResponseEntity.ok(postService.getUserPosts(username));
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<PostResponseDTO> approvePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.approvePost(id));
    }

    @GetMapping("/user-posts")
    public ResponseEntity<List<PostResponseDTO>> getUserPostsOnly() {
        return ResponseEntity.ok(postService.getAllUserPosts());
    }

    @GetMapping("/admin-posts")
    public ResponseEntity<List<PostResponseDTO>> getAdminPostsOnly() {
        return ResponseEntity.ok(postService.getAllAdminPosts());
    }

}

