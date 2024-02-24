package com.example.order.service;

import com.example.order.dtos.CategoryDTO;
import com.example.order.entities.Category;
import com.example.order.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        Category persistedCategory =  categoryRepository.save(category);
        return new CategoryDTO(persistedCategory.getCategoryId(), persistedCategory.getCategoryName());
    }

    public List<CategoryDTO> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(c -> new CategoryDTO(c.getCategoryId(),c.getCategoryName()))
                .collect(Collectors.toList());
    }



}

