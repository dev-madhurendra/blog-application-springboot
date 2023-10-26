package com.blog.application.apis.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "title", length = 50, nullable = false)
    @NotNull(message = "Title is required")
    @Size(min = 4, max = 50, message = "Title must be between 1 and 50 characters")
    private String categoryTitle;

    @Column(name = "description")
    @Size(max = 255, message = "Description should not exceed 255 characters")
    private String categoryDescription;

}
