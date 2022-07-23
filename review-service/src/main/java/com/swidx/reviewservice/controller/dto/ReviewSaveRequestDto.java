package com.swidx.reviewservice.controller.dto;


import com.swidx.reviewservice.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class ReviewSaveRequestDto {

    private static Long idx;
    private static String content;
    private static LocalDateTime createdBy;
    private static String image;

    @Builder
    public ReviewSaveRequestDto(Long idx, String content, LocalDateTime createdBy, String image){
        this.idx = idx;
        this.content = content;
        this.createdBy = createdBy;
        this.image = image;
    }

    @Builder
    public static Review toEntity(){
        return Review.builder()
                .idx(idx)
                .content(content)
                .createdBy(createdBy)
                .image(image)
                .build();
    }
}
