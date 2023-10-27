package com.blog.application.apis.entities;

import com.blog.application.apis.utils.AppConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = AppConstants.POST_TITLE, nullable = false, length = 100)
    @NotNull(message = AppConstants.POST_TITLE_REQUIRED)
    @Size(min = 5, max = 100, message = AppConstants.POST_TITLE_SIZE)
    private String title;

    @Column(nullable = false)
    @NotNull(message = AppConstants.POST_CONTENT_REQUIRED)
    private String content;

    private String imageName;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = AppConstants.CATEGORY_TABLE_ID)
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

}
