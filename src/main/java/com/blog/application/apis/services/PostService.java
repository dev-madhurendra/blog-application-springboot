package com.blog.application.apis.services;

import com.blog.application.apis.dtos.PostDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostService {

    PostDTO createPost(PostDTO postDTO,Long userId,Long categoryId);
    PostDTO updatePost(PostDTO postDTO,Long postId);
    void deletePost(Long postId);
    List<PostDTO> getAllPost();
    PostDTO getPostById(Long postId);
    List<PostDTO> getAllPostByCategory(Long categoryId);
    List<PostDTO> getAllPostByUser(Long userId);
    List<PostDTO> searchPosts(String keyword);
}
