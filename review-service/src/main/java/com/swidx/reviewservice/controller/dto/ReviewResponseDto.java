package com.swidx.reviewservice.controller.dto;

import com.swidx.reviewservice.domain.Review;

public class ReviewResponseDto {
    final Long idx;
    final String content;

    public ReviewResponseDto(Review entity){
        this.idx=entity.getidx();
        this.content = entity.getContent();
    }
}
