package com.blog.application.apis.repositories;

import com.blog.application.apis.entities.Like;
import com.blog.application.apis.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllLikesByPost(Post post);
}

