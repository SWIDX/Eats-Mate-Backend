package com.swidx.reviewservice.controller.dto;


import com.swidx.reviewservice.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class ReviewSaveRequestDto {

    private static Long id;
    private static String content;
    private static String category;
    private static LocalDateTime createdBy;
    private static String image;
    private static Long rate;

    @Builder
    public ReviewSaveRequestDto(Long id, String content, String category, LocalDateTime createdBy, String image, String rate){
        this.id = id;
        this.content = content;
        this.category = category;
        this.createdBy = createdBy;
        this.image = image;
        this.rate = rate;
    }

    @Builder
    public static Review toEntity(){
        return Review.builder()
                .id(id)
                .content(content)
                .category(category)
                .createdBy(createdBy)
                .image(image)
                .rate(rate)
                .build();
    }
}
