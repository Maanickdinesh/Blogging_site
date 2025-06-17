package com.example.mapper;


import com.example.dto.CategoryRequestDTO;
import com.example.dto.CategoryResponseDTO;
import com.example.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setRegion(dto.getRegion());
        category.setCreatedBy(dto.getCreatedBy());
        // You may want to set createdDate, updatedDate here using LocalDateTime.now()
        return category;
    }

    public static CategoryResponseDTO toDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setRegion(category.getRegion());
        dto.setCreatedBy(category.getCreatedBy());
        dto.setUpdatedBy(category.getUpdatedBy());
        dto.setCreatedDate(category.getCreatedDate());
        dto.setUpdatedDate(category.getUpdatedDate());
        return dto;
    }
}

