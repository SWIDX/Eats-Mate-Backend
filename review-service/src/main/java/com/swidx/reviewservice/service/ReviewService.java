package com.swidx.reviewservice.service;

import com.swidx.reviewservice.controller.dto.FeignUserInfoResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewSaveRequestDto;
import com.swidx.reviewservice.controller.dto.ReviewUpdateRequestDto;
import com.swidx.reviewservice.domain.Review;
import com.swidx.reviewservice.feign.client.JwtValidationClient;
import com.swidx.reviewservice.feign.client.ReviewUserInfoClient;
import com.swidx.reviewservice.image.S3Uploader;
import com.swidx.reviewservice.repository.ReviewRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private final JwtValidationClient jwtClient;
    private final ReviewUserInfoClient userInfoClient;
    private final ReviewRepository reviewRepository;
    private final S3Uploader s3Uploader;

    //crud

    //create
    public ResponseEntity save(String accessToken,
                               ReviewSaveRequestDto requestDto,
                               List<MultipartFile> mfiles) throws IOException {
        System.out.println("\n*** ReviewService: Review Save Request Info ***");
        System.out.println("placeName: " + requestDto.getPlaceName());
        System.out.println("category: " + requestDto.getCategory());
        System.out.println("content: " + requestDto.getContent());
        System.out.println("createdBy: " + requestDto.getCreatedBy());
        System.out.println("rate: " + requestDto.getRate());

        if (mfiles != null) {
            for (MultipartFile mfile : mfiles) {
                System.out.println("- image: " + mfile);
            }
        }
        System.out.println("");

        // feign client로 jwt 검증
        String email = null;
        try {
            email = jwtClient.getUserEmail(accessToken);
        } catch (FeignException e) {
            System.out.println(accessToken);
            System.out.println("*** ReviewService: JWT client failed ***");
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 나중에 e 상태 보고 코드 쪼개서 오류 처리해야 함
        }

        List<String> fileUrlList = new ArrayList<String>();
        if (mfiles != null) {
            fileUrlList = s3Uploader.upload(mfiles, "review");
        }

        Review review = ReviewSaveRequestDto.toEntity(email, fileUrlList, requestDto);
        reviewRepository.save(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // mypage 전용
    public ResponseEntity<List<ReviewResponseDto>> readAllUserReview(String accessToken) {
        // feign client로 jwt 검증
        String email = null;
        try {
            email = jwtClient.getUserEmail(accessToken);
        } catch (FeignException e) {
            System.out.println(accessToken);
            System.out.println("*** ReviewService: JWT client failed ***");
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Review> reviewList = reviewRepository.findAllByEmail(email);
        List<ReviewResponseDto> resList = new ArrayList<ReviewResponseDto>();

        for (Review review : reviewList) {
            // feign으로 리뷰 작성자 데이터 가져오기
            FeignUserInfoResponseDto dto = userInfoClient.getUserNameAndProfileImgUrl(email);
            resList.add(new ReviewResponseDto(dto.getUsername(), dto.getUserProfileImgUrl(), review));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(resList);
    }

    // detail, review 전용 (main page용 아님)
    public ResponseEntity<List<ReviewResponseDto>> readMultiple(String placeName, Long amount){
        List<Review> reviewList = new ArrayList<Review>();

        if (amount == 0) {
            reviewList = reviewRepository.findByPlaceNameOrderByCreatedByDesc(placeName);
        }
        else if (amount == 2) {
            reviewList = reviewRepository.findTop2ByPlaceNameOrderByCreatedByDesc(placeName);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ReviewResponseDto> resList = new ArrayList<ReviewResponseDto>();
        
        for (Review review : reviewList) {
            // feign으로 리뷰 작성자 데이터 가져오기
            FeignUserInfoResponseDto dto = userInfoClient.getUserNameAndProfileImgUrl(review.getEmail());
            resList.add(new ReviewResponseDto(dto.getUsername(), dto.getUserProfileImgUrl(), review));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(resList);
    }

    //count
    public List<Integer> countRate(String placeName){
        List<Integer> rateList = new ArrayList<Integer>();
        rateList.add(reviewRepository.countByPlaceNameAndRate(placeName, 0));
        rateList.add(reviewRepository.countByPlaceNameAndRate(placeName, 1));
        rateList.add(reviewRepository.countByPlaceNameAndRate(placeName, 2));
        return rateList;
    }

    //delete
    public void delete(Long id){
        reviewRepository.deleteById(id);
    }

}
