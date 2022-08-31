package com.swidx.reviewservice.controller.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {

    private String placeName;
    private String email;
    private String content;
    private String category;
    private String image;
    private Integer rate;
    private Integer recommend;
    


    @Builder
    public ReviewUpdateRequestDto(String placeName, String email, String content, String category, String image, Integer rate, Integer recommend){
        this.placeName = placeName;
        this.email = email;
        this.content = content;
        this.category = category;
        this.image = image;
        this.rate = rate;
        this.recommend = recommend;
    }
}
