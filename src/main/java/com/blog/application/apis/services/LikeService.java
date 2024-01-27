package com.blog.application.apis.services;

import com.blog.application.apis.dtos.LikeDTO;

import java.util.List;

public interface LikeService {
    LikeDTO likePost(Long postId,Long userId);
    void unlikePost(Long likeId);
    List<LikeDTO> getAllLikesByPostId(Long postId);
}
