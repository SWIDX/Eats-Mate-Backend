package com.swidx.reviewservice.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {

    private String content;
    private String image;


    @Builder
    public ReviewUpdateRequestDto(String content, String image){
        this.content = content;
        this.image = image;
    }
}
