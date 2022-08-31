package com.swidx.reviewservice.domain;


import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    public Review(Long id, String placeName, String email, String content, String category, String createdBy, String image, Integer rate, Integer recommend){
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

    public void update(String placeName, String email, String content, String category, String image, Integer rate, Integer recommend) {
        this.placeName=placeName;
        this.email=email;
        this.content = content;
        this.category = category;
        this.image = image;
        this.rate = rate;
        this.recommend = recommend;
    }

    public Long getid() {
        return this.id;
    }

    public String getPlaceName(){ return this.placeName; }

    public String getEmail(){ return this.email; }

    public String getContent() {
        return this.content;
    }
    
    public String getCategory(){
        return this.category;
    }

    public String getCreatedBy(){
        return this.createdBy;
    }

    public String getImage(){
        return this.image;
    }

    public Integer getRate(){
        return this.rate;
    }

    public Integer getRecommend() {return this.recommend; }

}

