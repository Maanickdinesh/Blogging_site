package com.example.mapper;


import com.example.dto.PostRequestDTO;
import com.example.dto.PostResponseDTO;
import com.example.entity.Category;
import com.example.entity.Post;
import com.example.utils.CommonConstant;

public class PostMapper {

    public static Post toEntity(PostRequestDTO request, Category category, String createdBy) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        post.setCategory(category);
        post.setStatus(CommonConstant.PostStatus.PENDING);
        post.setCreatedBy(createdBy);
        post.setCreatedDate(java.time.LocalDate.now().toString());
        return post;
    }

    public static PostResponseDTO toDto(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setImageUrl(post.getImageUrl());
        dto.setCategoryName(post.getCategory().getName());
        dto.setStatus(post.getStatus().toString());
        dto.setCreatedBy(post.getCreatedBy());
        dto.setCreatedDate(post.getCreatedDate());
        dto.setUpdatedDate(post.getUpdatedDate());
        return dto;
    }
}
