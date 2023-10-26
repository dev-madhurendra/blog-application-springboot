package com.blog.application.apis.controllers;

import com.blog.application.apis.dtos.CategoryDTO;
import com.blog.application.apis.payloads.ApiResponse;
import com.blog.application.apis.services.serviceimpl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @Autowired
    CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryDTO1 = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDTOS,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long id) {
        CategoryDTO categoryDTO1 = categoryService.updateCategory(categoryDTO,id);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully !",true),HttpStatus.OK);
    }

}
