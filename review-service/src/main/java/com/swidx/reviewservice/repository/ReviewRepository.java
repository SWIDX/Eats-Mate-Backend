package com.swidx.reviewservice.repository;

import com.swidx.reviewservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceNameOrderByCreatedByDesc(String placeName);
    List<Review> findTop2ByPlaceNameOrderByCreatedByDesc(String placeName);
}
