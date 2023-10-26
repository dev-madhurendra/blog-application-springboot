package com.blog.application.apis.dtos;

import com.blog.application.apis.utils.AppConstants;
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
    @NotNull(message = AppConstants.CATEGORY_TITLE_REQUIRED)
    @Size(min = 4, max = 50, message = AppConstants.CATEGORY_TITLE_SIZE)
    private String categoryTitle;
    @Size(max = 255, message = AppConstants.CATEGORY_TITLE_DESCRIPTION)
    private String categoryDescription;
}
