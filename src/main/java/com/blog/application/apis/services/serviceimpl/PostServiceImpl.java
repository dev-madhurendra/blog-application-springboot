package com.blog.application.apis.services.serviceimpl;

import com.blog.application.apis.dtos.CommentDTO;
import com.blog.application.apis.dtos.LikeDTO;
import com.blog.application.apis.dtos.PostDTO;
import com.blog.application.apis.entities.*;
import com.blog.application.apis.exceptions.ResourceNotFoundException;
import com.blog.application.apis.repositories.CategoryRepository;
import com.blog.application.apis.repositories.PostRepository;
import com.blog.application.apis.repositories.UserRepository;
import com.blog.application.apis.services.LikeService;
import com.blog.application.apis.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CommentServiceImpl commentService;
    private final LikeService likeService;

    @Autowired
    PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository, CommentServiceImpl commentService, LikeService likeService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO,Long userId,Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category",categoryId));

        Post post = modelMapper.map(postDTO,Post.class);
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post",postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost,PostDTO.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post",postId));
        postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> {
            List<CommentDTO> commentDTOList = commentService.getAllCommentsOfPost(post.getPostId());
            PostDTO postDTO = modelMapper.map(post,PostDTO.class);
            postDTO.setComments(commentDTOList);

            List<LikeDTO> likeDTOList = likeService.getAllLikesByPostId(post.getPostId());
            log.info(">>> " + likeDTOList);
            postDTO.setLikes(likeDTOList);
            return postDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post",postId));
        List<CommentDTO> commentDTOList = commentService.getAllCommentsOfPost(postId);
        List<LikeDTO> likeDTOList = likeService.getAllLikesByPostId(postId);
        log.info(">>> getting all comments of post " + commentDTOList);
        log.info(">>> getting all likes of post " + likeDTOList);
        PostDTO postDTOs = modelMapper.map(post,PostDTO.class);
        postDTOs.setComments(commentDTOList);
        postDTOs.setLikes(likeDTOList);
        return postDTOs;
    }

    @Override
    public List<PostDTO> getAllPostByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category",categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map(post -> modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<PostDTO> getAllPostByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User",userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(post -> modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContaining(keyword);
        return posts.stream()
                .map(post -> modelMapper.map(post,PostDTO.class))
                .collect(Collectors.toList());
    }

}
