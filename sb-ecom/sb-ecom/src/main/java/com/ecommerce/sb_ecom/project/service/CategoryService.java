package com.ecommerce.sb_ecom.project.service;

import com.ecommerce.sb_ecom.project.payload.CategoryDTO;
import com.ecommerce.sb_ecom.project.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
