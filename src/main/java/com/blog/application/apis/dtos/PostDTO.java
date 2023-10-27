package com.blog.application.apis.dtos;

import com.blog.application.apis.utils.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long postId;

    @NotNull(message = AppConstants.POST_TITLE_REQUIRED)
    @Size(min = 4, max = 100, message = AppConstants.POST_TITLE_SIZE)
    private String title;

    @NotNull(message = AppConstants.POST_CONTENT_REQUIRED)
    private String content;

    private String imageName = AppConstants.POST_IMAGE_DEFAULT;

    private UserDTO user;
    private CategoryDTO category;
    private String createdAt;
    private String updatedAt;
    private List<CommentDTO> comments = new ArrayList<>();
}
