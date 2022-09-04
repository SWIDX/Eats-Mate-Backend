package com.swidx.reviewservice.service;

import com.swidx.reviewservice.controller.dto.ReviewResponseDto;
import com.swidx.reviewservice.controller.dto.ReviewSaveRequestDto;
import com.swidx.reviewservice.controller.dto.ReviewUpdateRequestDto;
import com.swidx.reviewservice.domain.Review;
import com.swidx.reviewservice.feign.client.JwtValidationClient;
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
    public void update(Long id, ReviewUpdateRequestDto requestDto){
        // feign으로 jwt 검증
        String email = "";

        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("*** ReviewService: 해당 리뷰 id가 존재하지 않습니다. ***")
        );
        review.update(email, requestDto);
    }

    //delete
    public void delete(Long id){
        reviewRepository.deleteById(id);
    }

}
