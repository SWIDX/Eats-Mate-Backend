package com.swidx.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter // Response로 날라가는 Dto에 getter 없으면 406 오류
public class LoginResponseDto {
    private String name;
    private String email;
    private String profileImageUrl;
    private String accessToken;
    private Long expirationTime;
}
