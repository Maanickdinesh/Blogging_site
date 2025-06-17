package com.example.service;

import java.util.List;

import com.example.dto.PostRequestDTO;
import com.example.dto.PostResponseDTO;

public interface PostService {
    PostResponseDTO createPost(PostRequestDTO request, String createdBy);
    List<PostResponseDTO> getAllPublishedPosts();
    List<PostResponseDTO> getUserPosts(String username);
    List<PostResponseDTO> getAllUserPosts();
    List<PostResponseDTO> getAllAdminPosts();
    
    List<PostResponseDTO> getPendingPosts();
    PostResponseDTO approvePost(Long id);
    //List<PostResponseDTO> getPostsByRoleAndStatus(String role, String status);



    
}
