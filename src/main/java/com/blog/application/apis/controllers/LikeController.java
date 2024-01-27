package com.blog.application.apis.controllers;

import com.blog.application.apis.dtos.LikeDTO;
import com.blog.application.apis.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<LikeDTO> likePost(
            @PathVariable Long postId,
            @PathVariable Long userId
    ) {
        LikeDTO likeDTO = likeService.likePost(postId,userId);
        return new ResponseEntity<>(likeDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<?> unlikePost(@PathVariable Long likeId) {
        likeService.unlikePost(likeId);
        return new ResponseEntity<>("You unliked the post !",HttpStatus.OK);
    }

}
