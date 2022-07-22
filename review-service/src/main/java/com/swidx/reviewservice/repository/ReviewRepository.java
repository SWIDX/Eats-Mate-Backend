package com.swidx.reviewservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swidx.reviewservice.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
