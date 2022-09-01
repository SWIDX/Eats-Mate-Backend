package com.swidx.userservice.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    Optional<Like> findByEmailAndPlaceId(String email, Long placeId); // WHERE .. AND ..
    void deleteByEmailAndPlaceId(@Param("email") String email, @Param("placeId") Long placeId);
}
