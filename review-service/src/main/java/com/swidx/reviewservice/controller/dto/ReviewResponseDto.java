package com.swidx.reviewservice.controller.dto;

import com.swidx.reviewservice.domain.Review;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ReviewResponseDto {
    private Long id;
    private String username;
    private String userProfileImgUrl;
    private String placeName;
    private String category;
    private String content;
    private List<String> images;
    private String createdBy;
    private Integer rate;
    private Integer recommend;
    

    public ReviewResponseDto(String username, String userProfileImgUrl, Review entity){
        this.id = entity.getId();
        this.username = username;
        this.userProfileImgUrl = userProfileImgUrl;
        this.placeName = entity.getPlaceName();
        this.category = entity.getCategory();
        this.content = entity.getContent();
        this.images = Arrays.asList(entity.getImage().split("\\s*,\\s*"));
        this.createdBy = entity.getCreatedBy();
        this.rate = entity.getRate();
        this.recommend = entity.getRecommend();

    }
}
