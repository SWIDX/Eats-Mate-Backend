package com.swidx.userservice.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findByEmailAndPlaceId(String email, Long placeId); // WHERE .. AND ..
    Optional<Like> deleteByEmailAndPlaceId(String email, Long placeId);
}
