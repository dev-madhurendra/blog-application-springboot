package com.blog.application.apis.entities;

import com.blog.application.apis.utils.AppConstants;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = AppConstants.LIKE_TABLE)
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}
