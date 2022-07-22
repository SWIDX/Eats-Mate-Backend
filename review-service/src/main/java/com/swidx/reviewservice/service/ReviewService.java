package com.swidx.reviewservice.service;

import com.swidx.reviewservice.controller.dto.ReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewSaveRequestDto;
import com.swidx.reviewservice.controller.dto.ReviewUpdateRequestDto;
import com.swidx.reviewservice.domain.Review;
import com.swidx.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    //crud

    //create
    public ReviewResponseDto save(ReviewSaveRequestDto requestDto){
        Review review = ReviewSaveRequestDto.toEntity();
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    //read
    public ReviewResponseDto readOne(Long idx){
        Review review = reviewRepository.findById(idx).orElse(null);
        return new ReviewResponseDto(review);
    }

    public List<Review> readAll(){
        List<Review> listReview = reviewRepository.findAll();
        return listReview;
    }

    //update
    public Long update(Long idx, ReviewUpdateRequestDto requestDto){
        Review review = reviewRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("일치하는 순서가 없습니다"));
        review.update(requestDto.getContent(), requestDto.getImage());
        return idx;
    }

    //delete
    public void delete(Long idx){
        reviewRepository.deleteById(idx);
    }

}
