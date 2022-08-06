package com.swidx.reviewservice.controller.dto;


import com.swidx.reviewservice.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class ReviewSaveRequestDto {

    private static String content;

    private static String category;

    private static LocalDateTime createdBy;

    private static String image;

    private static Long rate;

    @Builder
    public static Review toEntity(){
        return Review.builder()
                .content(content)
                .category(category)
                .createdBy(createdBy)
                .image(image)
                .rate(rate)
                .build();
    }
}
