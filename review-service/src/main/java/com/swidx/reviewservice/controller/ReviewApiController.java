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
@RequestMapping("review-service")
public class ReviewApiController {
    private final ReviewService reviewService;

    //create
    @PostMapping("/review")
    public ResponseEntity createReview(@RequestHeader("Authorization") String authorization,
                                       @RequestBody ReviewSaveRequestDto requestDto){
        String accessToken = authorization.replace("Bearer ", "");
        return reviewService.save(accessToken, requestDto);
    }

    //read
    @GetMapping("/review/{id}")
    public ResponseEntity<ReviewResponseDto> readReview(@PathVariable Long id){
        return new ResponseEntity<>(reviewService.readOne(id),HttpStatus.OK);
    }

    @GetMapping("/review")
    public List<Review> readAllReview(){
        List<Review> listReview = reviewService.readAll();
        return listReview;
    }


    //update
    @PutMapping("/review/{id}")
    public void updateReview(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto){
        reviewService.update(id, requestDto);
    }

    //delete
    @DeleteMapping("review/{id}")
    public ResponseEntity deleteReview(@PathVariable Long id){
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
