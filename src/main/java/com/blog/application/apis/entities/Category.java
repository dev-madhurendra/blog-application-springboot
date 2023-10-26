package com.blog.application.apis.entities;

import com.blog.application.apis.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Entity
@Table(name = AppConstants.CATEGORY)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = AppConstants.CATEGORY_TITLE, length = 50, nullable = false)
    @NotNull(message = AppConstants.CATEGORY_TITLE_REQUIRED)
    @Size(min = 4, max = 50, message = AppConstants.CATEGORY_TITLE_SIZE)
    private String categoryTitle;

    @Column(name = AppConstants.CATEGORY_DESCRIPTION)
    @Size(max = 255, message = AppConstants.CATEGORY_TITLE_DESCRIPTION)
    private String categoryDescription;

    @OneToMany(mappedBy = AppConstants.CATEGORY,cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

}
