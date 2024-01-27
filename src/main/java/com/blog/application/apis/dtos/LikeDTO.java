package com.blog.application.apis.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeDTO {
    private Long likeId;
    private RequestUser userDTO;
    private PostDTO postDTO;

}
