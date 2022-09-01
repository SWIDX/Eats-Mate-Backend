package com.swidx.userservice.service;

import com.swidx.userservice.domain.like.Like;
import com.swidx.userservice.domain.like.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository db;

    public boolean checkUserLike(String email, Long placeId) {
        Optional<Like> entity = db.findByEmailAndPlaceId(email, placeId);
        return entity.isPresent();
    }

    public void addUserLike(String email, Long placeId) {
        db.save(new Like(email, placeId));
    }

    @Transactional
    public void deleteUserLike(String email, Long placeId) {
        db.deleteByEmailAndPlaceId(email, placeId);
    }
}
