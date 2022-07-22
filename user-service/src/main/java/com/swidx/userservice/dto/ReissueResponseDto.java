package com.swidx.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ReissueResponseDto {
    private String accessToken;
    private Long expirationTime;
}
