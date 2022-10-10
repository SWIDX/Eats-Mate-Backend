package com.swidx.reviewservice.controller.dto;

import com.swidx.reviewservice.domain.Review;
import lombok.Getter;

import java.util.List;

@Getter
public class RecentReviewResponseDto {
    private Integer id;
    private String name;
    private String gubun;
    private String gugun;
    private String address;
    private String time;
    private List<String> image;
    private List<Review> review;


    public RecentReviewResponseDto(FeignRestInformationDto rinfo, List<Review> reviewList){
        this.id = rinfo.getId();
        this.name = rinfo.getName();
        this.gubun = rinfo.getGubun();
        this.gugun = rinfo.getGugun();
        this.address = rinfo.getAddress();
        this.time = rinfo.getUsage_of_week_and_time();
        this.image = rinfo.getImage();
        this.review = reviewList;
    }
}
