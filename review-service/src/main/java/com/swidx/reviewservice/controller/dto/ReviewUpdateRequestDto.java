package com.swidx.reviewservice.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {

    private String content;
    private String category;
    private String image;
    private Long rate;


    @Builder
    public ReviewUpdateRequestDto(String content, String category, String image, Long rate){
        this.content = content;
        this.category = category;
        this.image = image;
        this.rate = rate;
    }
}
