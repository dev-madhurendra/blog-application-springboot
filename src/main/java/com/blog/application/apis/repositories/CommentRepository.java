package com.blog.application.apis.repositories;

import com.blog.application.apis.entities.Comment;
import com.blog.application.apis.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllCommentsByPost(Post post);
}
