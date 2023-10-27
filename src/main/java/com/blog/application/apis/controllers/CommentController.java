package com.blog.application.apis.controllers;

import com.blog.application.apis.dtos.CommentDTO;
import com.blog.application.apis.payloads.ApiResponse;
import com.blog.application.apis.services.serviceimpl.CommentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentServiceImpl commentService;


    @Autowired
    CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    @PostMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Long postId, @PathVariable Long userId) {
        CommentDTO commentDTO1 = commentService.createComment(commentDTO,postId,userId);
        return new ResponseEntity<>(commentDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsOfPost(@PathVariable Long postId) {
        List<CommentDTO> commentDTOList = commentService.getAllCommentsOfPost(postId);
        return new ResponseEntity<>(commentDTOList,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted successfully !",true),HttpStatus.OK);
    }
}
