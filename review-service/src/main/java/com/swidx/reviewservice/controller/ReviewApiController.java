package com.swidx.reviewservice.controller;


import com.swidx.reviewservice.controller.dto.ReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewSaveRequestDto;
import com.swidx.reviewservice.controller.dto.ReviewUpdateRequestDto;
import com.swidx.reviewservice.domain.Review;
import com.swidx.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewApiController {
    private final ReviewService reviewService;

    //create
    @PostMapping()
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewSaveRequestDto requestDto){
        return new ResponseEntity<>(reviewService.save(requestDto), HttpStatus.OK);
    }

    //read
    @GetMapping(value = "/{idx}")
    public ResponseEntity<ReviewResponseDto> readReview(@PathVariable Long idx){
        return new ResponseEntity<>(reviewService.readOne(idx),HttpStatus.OK);
    }

    @GetMapping()
    public List<Review> readAllReview(){
        List<Review> listReview = reviewService.readAll();
        return listReview;
    }


    //update
    @PutMapping("/{idx}")
    public ResponseEntity updateReview(@PathVariable Long idx, @RequestBody ReviewUpdateRequestDto requestDto){
        return new ResponseEntity<>(reviewService.update(idx, requestDto),HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{idx}")
    public ResponseEntity deleteReview(@PathVariable Long idx){
        reviewService.delete(idx);
        return ResponseEntity.noContent().build();
    }


}
