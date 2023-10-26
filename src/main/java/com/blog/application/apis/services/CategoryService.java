package com.blog.application.apis.services;

import com.blog.application.apis.dtos.CategoryDTO;
import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Long id);
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryById(Long id);
    void deleteCategory(Long id);
}
