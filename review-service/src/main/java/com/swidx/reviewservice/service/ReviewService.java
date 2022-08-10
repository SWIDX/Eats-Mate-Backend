package com.swidx.reviewservice.service;

import com.swidx.reviewservice.controller.dto.ReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewSaveRequestDto;
import com.swidx.reviewservice.controller.dto.ReviewUpdateRequestDto;
import com.swidx.reviewservice.domain.Review;
import com.swidx.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public ReviewResponseDto readOne(Long id){
        Review review = reviewRepository.findById(id).orElse(null);
        return new ReviewResponseDto(review);
    }

    public List<Review> readAll(){
        List<Review> listReview = reviewRepository.findAll();
        return listReview;
    }

    //update
    public Long update(Long idx, ReviewUpdateRequestDto requestDto){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 순서가 없습니다"));
        review.update(requestDto.getContent(), requestDto.getCategory(), requestDto.getImage(), requestDto.getRate());
        return id;
    }

    //delete
    public void delete(Long id){
        reviewRepository.deleteById(id);
    }

}
