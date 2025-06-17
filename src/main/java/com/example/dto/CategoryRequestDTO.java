package com.example.dto;


import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String name;
    private String description;
    private String region;
    private String createdBy;
}

