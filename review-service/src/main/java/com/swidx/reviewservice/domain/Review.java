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
    private Long idx; //글 순서

    @Column
    private String content;

    @Column
    private LocalDateTime createdBy;

    @Column
    private String image;



    @Builder
    public Review(Long idx, String content, LocalDateTime createdBy, String image){
        this.idx = idx;
        this.content = content;
        this.createdBy = createdBy;
        this.image = image;
    }

    public void update(String content, String image) {
        this.content = content;
        this.image = image;
    }

    public Long getidx() {
        return this.idx;
    }

    public String getContent() {
        return this.content;
    }
}
