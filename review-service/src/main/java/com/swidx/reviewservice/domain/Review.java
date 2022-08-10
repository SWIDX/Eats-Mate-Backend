package com.swidx.reviewservice.domain;


import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //글 순서

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private LocalDateTime createdBy;

    @Column
    private String image;

    @Column
    private Long rate;


    @Builder
    public Review(Long id, String content, String category, LocalDateTime createdBy, String image, Long rate){
        this.id = id;
        this.content = content;
        this.category = category;
        this.createdBy = createdBy;
        this.image = image;
        this.rate = rate;
    }

    public void update(String content, String category, String image, String rate) {
        this.content = content;
        this.category = category;
        this.image = image;
        this.rate = rate;
    }

    public Long getid() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }
    
    public String getCategory(){
        return this.category;
    }

    public LocalDateTime getCreatedBy(){
        return this.createdBy;
    }

    public String getImage(){
        return this.image;
    }

    public Long getRate(){
        return this.rate;
    }
}

