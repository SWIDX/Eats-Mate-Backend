package com.swidx.userservice.dto;

import com.swidx.userservice.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class FeignUserInfoResponseDto {
    private String username;
    private String userProfileImgUrl;

    public FeignUserInfoResponseDto(User user) {
        this.username = user.getName();
        this.userProfileImgUrl = user.getProfileImageUrl();
    }
}
