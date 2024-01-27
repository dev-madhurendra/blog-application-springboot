package com.blog.application.apis.repositories;

import com.blog.application.apis.dtos.PostDTO;
import com.blog.application.apis.entities.Category;
import com.blog.application.apis.entities.Post;
import com.blog.application.apis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
