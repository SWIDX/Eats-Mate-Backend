package com.swidx.reviewservice.controller.dto;


import com.swidx.reviewservice.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@ToString
@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String placeName;
    private String gugun;
    private String content;
    private String category;
    private String createdBy;
    private Integer rate;

    @Builder
    public ReviewSaveRequestDto(String placeName,
                                String gugun,
                                String content,
                                String category,
                                String createdBy,
                                Integer rate
    ){
        this.placeName = placeName;
        this.gugun = gugun;
        this.content = content;
        this.category = category;
        this.createdBy = createdBy;
        this.rate = rate;
    }

    @Builder
    public static Review toEntity(String email, List<String> fileUrlList, ReviewSaveRequestDto dto){
        String urlString = String.join(",", fileUrlList);

        return Review.builder()
                .placeName(dto.getPlaceName())
                .gugun(dto.getGugun())
                .email(email)
                .content(dto.getContent())
                .category(dto.getCategory())
                .image(urlString)
                .createdBy(dto.getCreatedBy())
                .rate(dto.getRate())
                .recommend(0)
                .build();
    }
}
