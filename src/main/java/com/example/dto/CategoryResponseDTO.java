package com.example.dto;

import lombok.Data;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String region;
    private String createdBy;
    private String updatedBy;
    private String createdDate;
    private String updatedDate;
}