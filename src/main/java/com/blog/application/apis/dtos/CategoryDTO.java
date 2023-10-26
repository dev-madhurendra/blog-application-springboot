package com.blog.application.apis.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long categoryId;
    @NotNull(message = "Title is required")
    @Size(min = 4, max = 50, message = "Title must be between 1 and 50 characters")
    private String categoryTitle;
    @Size(max = 255, message = "Description should not exceed 255 characters")
    private String categoryDescription;
}
