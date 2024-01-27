package com.blog.application.apis.services.serviceimpl;

import com.blog.application.apis.dtos.LikeDTO;
import com.blog.application.apis.dtos.PostDTO;
import com.blog.application.apis.dtos.RequestUser;
import com.blog.application.apis.dtos.UserDTO;
import com.blog.application.apis.entities.Like;
import com.blog.application.apis.entities.Post;
import com.blog.application.apis.entities.User;
import com.blog.application.apis.exceptions.ResourceNotFoundException;
import com.blog.application.apis.repositories.LikeRepository;
import com.blog.application.apis.repositories.PostRepository;
import com.blog.application.apis.repositories.UserRepository;
import com.blog.application.apis.services.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LikeServiceImpl(
            LikeRepository likeRepository,
            PostRepository postRepository,
            UserRepository userRepository,
            ModelMapper modelMapper
    ) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public LikeDTO likePost(Long postId, Long userId) {
        log.info(">>> User " + userId + " liked the post " + postId);
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post " , postId));
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User " , userId));
        Like like = Like
                .builder()
                .post(post)
                .user(user)
                .build();
        Like savedLike = likeRepository.save(like);
        LikeDTO likeDTO =  modelMapper.map(savedLike, LikeDTO.class);
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        RequestUser userDTO = modelMapper.map(user,RequestUser.class);
        likeDTO.setPostDTO(postDTO);
        likeDTO.setUserDTO(userDTO);
        return likeDTO;
    }

    @Override
    public void unlikePost(Long likeId) {
        likeRepository
                .findById(likeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Like ", likeId)
                );
        likeRepository.deleteById(likeId);
    }

    @Override
    public List<LikeDTO> getAllLikesByPostId(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", postId));
        List<Like> like = likeRepository.findAllLikesByPost(post);
        return like
                .stream()
                .map(like1 -> {
                    return modelMapper.map(like1,LikeDTO.class);
                }).collect(Collectors.toList());
    }
}
