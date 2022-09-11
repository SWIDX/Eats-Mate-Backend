package com.swidx.reviewservice.repository;

import com.swidx.reviewservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceNameOrderByCreatedByDesc(String placeName);
    List<Review> findTop2ByPlaceNameOrderByCreatedByDesc(String placeName);
    Integer countByPlaceNameAndRate(String placeName, Integer rate);
    List<Review> findAllByEmail(String email);

    @Query(value = "SELECT place_name FROM review WHERE gugun=:gugun ORDER BY created_by DESC LIMIT 1;", nativeQuery = true)
    String getRecentPlaceNameof(String gugun);

    List<Review> findTop3ByPlaceNameOrderByCreatedByDesc(String placeName);
}
