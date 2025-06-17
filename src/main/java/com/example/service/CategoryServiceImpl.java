package com.example.service;


import com.example.dto.CategoryRequestDTO;
import com.example.dto.CategoryResponseDTO;
import com.example.entity.Category;
import com.example.mapper.CategoryMapper;
import com.example.repo.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        Category category = CategoryMapper.toEntity(requestDTO);
        category.setCreatedDate(LocalDateTime.now().toString());
        category.setUpdatedDate(LocalDateTime.now().toString());

        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}

