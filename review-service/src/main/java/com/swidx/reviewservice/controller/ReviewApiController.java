package com.swidx.reviewservice.controller;


import com.swidx.reviewservice.controller.dto.RecentReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewSaveRequestDto;
import com.swidx.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/review") // -1 for all, 2 for top 2
    public ResponseEntity<List<ReviewResponseDto>> readMultiple(
            @RequestParam("place_name") String placeName,
            @RequestParam("amount") Long amount,
            @RequestParam(value="recommend", required=false, defaultValue="false") Boolean recommend){
        return reviewService.readMultiple(placeName, amount, recommend);
    }

    //read
    @GetMapping("/review/user")
    public ResponseEntity<List<ReviewResponseDto>> readAllUserReview(
            @RequestHeader("Authorization") String authorization){
        String accessToken = authorization.replace("Bearer ", "");
        return reviewService.readAllUserReview(accessToken);
    }

    //read
    @GetMapping("/review/recent")
    public List<RecentReviewResponseDto> readRecentReview(){
        return reviewService.readThreeRecentReview();
    }

    //count
    @GetMapping("/review/count")
    public List<Integer> updateReview(@RequestParam("place_name") String placeName){
        return reviewService.countRate(placeName);
    }

    //delete
    @DeleteMapping("review/{id}")
    public ResponseEntity deleteReview(@PathVariable Long id){
        // jwt 검증 로직 추가 필요
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
