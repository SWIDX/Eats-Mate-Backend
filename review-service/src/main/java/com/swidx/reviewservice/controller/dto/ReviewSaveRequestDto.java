package com.swidx.reviewservice.controller.dto;


import com.swidx.reviewservice.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

public class ReviewSaveRequestDto {

    private static Long id;
    private static String placeName;
    private static String email;
    private static String content;
    private static String category;
    private static String createdBy;
    private static String image;
    private static Integer rate;
    private static Integer recommend;

    @Builder
    public ReviewSaveRequestDto(Long id, String placeName, String email, String content, String category, String createdBy, String image, Integer rate, Integer recommend){
        this.id = id;
        this.placeName = placeName;
        this.email = email;
        this.content = content;
        this.category = category;
        this.createdBy = createdBy;
        this.image = image;
        this.rate = rate;
        this.recommend = recommend;
    }

    @Builder
    public static Review toEntity(){
        return Review.builder()
                .id(id)
                .placeName(placeName)
                .email(email)
                .content(content)
                .category(category)
                .createdBy(createdBy)
                .image(image)
                .rate(rate)
                .recommend(recommend)
                .build();
    }
}
