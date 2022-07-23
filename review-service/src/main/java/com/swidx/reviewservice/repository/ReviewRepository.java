package com.swidx.reviewservice.repository;

import com.swidx.reviewservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
