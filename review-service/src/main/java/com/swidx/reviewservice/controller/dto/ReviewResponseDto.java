package com.swidx.reviewservice.controller.dto;

import com.swidx.reviewservice.domain.Review;

public class ReviewResponseDto {
    final Long id;
    final String content;
    final String category;
    final LocalDateTime createdBy;
    final String image;
    final Long rate;
    

    public ReviewResponseDto(Review entity){
        this.id=entity.getid();
        this.content = entity.getContent();
        this.category = entity.getCategory();
        this.createdBy = entity.getCreatedBy();
        this.image = entity.getImage();
        this.rate = entity.getRate();

    }
}
