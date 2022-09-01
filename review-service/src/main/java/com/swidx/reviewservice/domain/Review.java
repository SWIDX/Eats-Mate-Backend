package com.swidx.reviewservice.domain;


import com.swidx.reviewservice.controller.dto.ReviewUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //글 순서

    @Column
    private String placeName;

    @Column
    private String email;

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private String createdBy;

    @Column
    private String image;

    @Column
    private Integer rate;

    @Column
    private Integer recommend;


    @Builder
    public Review(
            String placeName,
            String email,
            String content,
            String category,
            String createdBy,
            String image,
            Integer rate,
            Integer recommend
    ){
        this.placeName = placeName;
        this.email = email;
        this.content = content;
        this.category = category;
        this.createdBy = createdBy;
        this.image = image;
        this.rate = rate;
        this.recommend = recommend;
    }

    public void update(String email, ReviewUpdateRequestDto dto) {
        this.email = email;
        this.content = dto.getContent();
        this.image = dto.getImage();
        this.rate = dto.getRate();
    }

}

