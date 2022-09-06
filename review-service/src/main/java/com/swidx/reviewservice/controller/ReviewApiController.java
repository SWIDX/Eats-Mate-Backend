package com.swidx.reviewservice.controller;


import com.swidx.reviewservice.controller.dto.ReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewSaveRequestDto;
import com.swidx.reviewservice.controller.dto.ReviewUpdateRequestDto;
import com.swidx.reviewservice.domain.Review;
import com.swidx.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("review-service")
public class ReviewApiController {
    private final ReviewService reviewService;

    //create
    @PostMapping(value = "/review", consumes = {"multipart/form-data"})
    public ResponseEntity createReview(@RequestHeader("Authorization") String authorization,
                                       @RequestPart("json") ReviewSaveRequestDto requestDto,
                                       @RequestPart("image") @Nullable List<MultipartFile> mfiles) throws IOException {
        String accessToken = authorization.replace("Bearer ", "");
        return reviewService.save(accessToken, requestDto, mfiles);
    }

    //read
    @GetMapping("/review/{placeName}/{amount}") // -1 for all, 2 for top 2
    public ResponseEntity<List<ReviewResponseDto>> readMultiple(@PathVariable String placeName, @PathVariable Long amount){
        return reviewService.readMultiple(placeName, amount);
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
