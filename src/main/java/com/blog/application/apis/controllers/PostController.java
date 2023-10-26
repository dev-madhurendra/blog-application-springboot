package com.blog.application.apis.controllers;

import com.blog.application.apis.dtos.PostDTO;
import com.blog.application.apis.payloads.ApiResponse;
import com.blog.application.apis.services.FileService;
import com.blog.application.apis.services.serviceimpl.PostServiceImpl;
import com.blog.application.apis.utils.AppConstants;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(AppConstants.POST_BASE_URL)
public class PostController {

    private final PostServiceImpl postService;
    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    PostController(PostServiceImpl postService,FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
    }

    @PostMapping(AppConstants.POST_CREATE_URL)
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long userId, @PathVariable Long categoryId) {
        PostDTO postDTO1 = postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(postDTO1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>>  getAllPosts() {
        List<PostDTO> postDTOS = postService.getAllPost();
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @GetMapping(AppConstants.POST_ID)
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    @GetMapping(AppConstants.POST_CATEGORY_ID)
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Long categoryId) {
        List<PostDTO> postDTOS = postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @GetMapping(AppConstants.POST_USER_ID)
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Long userId) {
        List<PostDTO> postDTOS = postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @DeleteMapping(AppConstants.POST_ID)
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully !",true),HttpStatus.OK);
    }

    @PutMapping(AppConstants.POST_ID)
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Long postId) {
        PostDTO postDTO1 = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(postDTO1,HttpStatus.OK);
    }

    @GetMapping(AppConstants.POST_SEARCH)
    public ResponseEntity<List<PostDTO>> searchPost(@RequestParam String title) {
        List<PostDTO> postDTOS = postService.searchPosts(title);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @PostMapping("/{postId}/upload/image")
    public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Long postId) throws IOException {
        PostDTO postDTO = postService.getPostById(postId);
        String fileName = fileService.uploadImage(this.path,image);
        postDTO.setImageName(fileName);
        PostDTO updatedPost = postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse res) throws IOException {
        InputStream resource = fileService.getResource(path,imageName);
        res.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,res.getOutputStream());
    }
}
