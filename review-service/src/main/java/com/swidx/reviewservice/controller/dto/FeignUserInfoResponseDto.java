package com.swidx.reviewservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeignUserInfoResponseDto {
    private String username;
    private String userProfileImgUrl;
}
