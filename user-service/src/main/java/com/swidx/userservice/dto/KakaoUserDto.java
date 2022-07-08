package com.swidx.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserDto {
    private String id;
    private String name;
    private String email;
    private String profileImageUrl;
}
