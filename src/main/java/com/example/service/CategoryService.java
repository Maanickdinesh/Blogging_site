package com.example.service;

import java.util.List;

import com.example.dto.CategoryRequestDTO;
import com.example.dto.CategoryResponseDTO;

public interface CategoryService {
	CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO);
    List<CategoryResponseDTO> getAllCategories();

}
