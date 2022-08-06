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
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReviewResponseDto> readReview(@PathVariable Long id){
        return new ResponseEntity<>(reviewService.readOne(id),HttpStatus.OK);
    }

    @GetMapping()
    public List<Review> readAllReview(){
        List<Review> listReview = reviewService.readAll();
        return listReview;
    }


    //update
    @PutMapping("/{id}")
    public ResponseEntity updateReview(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto){
        return new ResponseEntity<>(reviewService.update(id, requestDto),HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReview(@PathVariable Long id){
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
