package com.swidx.userservice.service;

import com.swidx.userservice.domain.like.Like;
import com.swidx.userservice.domain.like.LikeRepository;
import com.swidx.userservice.dto.FeignInformationDto;
import com.swidx.userservice.dto.LikeAllResponseDto;
import com.swidx.userservice.feign.client.MapGetInformationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository db;
    private final MapGetInformationClient mapInfoClient;

    public boolean checkUserLike(String email, Long placeId) {
        Optional<Like> entity = db.findByEmailAndPlaceId(email, placeId);
        return entity.isPresent();
    }

    public ResponseEntity<List<LikeAllResponseDto>> getUserLike(String email) {
        List<Like> entities = db.findAllByEmail(email);
        List<LikeAllResponseDto> allLikeList = new ArrayList<LikeAllResponseDto>();
        for (Like like : entities) {
            FeignInformationDto information = mapInfoClient.getRestInfo(like.getPlaceId().intValue());
            allLikeList.add(new LikeAllResponseDto(
                    information.getId(),
                    information.getName(),
                    information.getGubun(),
                    information.getAddress()
            ));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(allLikeList);
    }

    public void addUserLike(String email, Long placeId) {
        db.save(new Like(email, placeId));
    }

    @Transactional
    public void deleteUserLike(String email, Long placeId) {
        db.deleteByEmailAndPlaceId(email, placeId);
    }
}
