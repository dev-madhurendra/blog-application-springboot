package com.blog.application.apis.services.serviceimpl;

import com.blog.application.apis.dtos.CommentDTO;
import com.blog.application.apis.dtos.PostDTO;
import com.blog.application.apis.dtos.UserDTO;
import com.blog.application.apis.entities.Comment;
import com.blog.application.apis.entities.Post;
import com.blog.application.apis.entities.User;
import com.blog.application.apis.exceptions.ResourceNotFoundException;
import com.blog.application.apis.repositories.CommentRepository;
import com.blog.application.apis.repositories.PostRepository;
import com.blog.application.apis.repositories.UserRepository;
import com.blog.application.apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    CommentServiceImpl(CommentRepository commentRepository,ModelMapper modelMapper,PostRepository postRepository
    ,UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post",postId));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User",userId));
        Comment comment = modelMapper.map(commentDTO,Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment" , commentId));
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDTO> getAllCommentsOfPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post",postId));
        List<Comment> commentList = commentRepository.findAllCommentsByPost(post);
        return commentList.stream().map(comment -> modelMapper.map(comment,CommentDTO.class)).collect(Collectors.toList());
    }
}
