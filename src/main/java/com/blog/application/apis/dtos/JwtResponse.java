package com.blog.application.apis.dtos;

import com.blog.application.apis.utils.AppConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
    private String message;
}
