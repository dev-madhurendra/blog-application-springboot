package com.blog.application.apis.services;

import com.blog.application.apis.dtos.CommentDTO;
import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO,Long postId,Long userId);
    void deleteComment(Long commentId);
    List<CommentDTO> getAllCommentsOfPost(Long postId);
}
