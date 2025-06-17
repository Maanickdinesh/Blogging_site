package com.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String categoryName;
    private String status;
    private String createdBy;
    private String createdDate;
    private String updatedDate;
}

