package com.swidx.reviewservice.controller.dto;

import com.swidx.reviewservice.domain.Review;

public class ReviewResponseDto {
    final Long id;
    final String placeName;
    final String email;
    final String content;
    final String category;
    final String createdBy;
    final String image;
    final Integer rate;
    final Integer recommend;
    

    public ReviewResponseDto(Review entity){
        this.id=entity.getid();
        this.placeName = entity.getPlaceName();
        this.email = entity.getEmail();
        this.content = entity.getContent();
        this.category = entity.getCategory();
        this.createdBy = entity.getCreatedBy();
        this.image = entity.getImage();
        this.rate = entity.getRate();
        this.recommend = entity.getRecommend();

    }
}
