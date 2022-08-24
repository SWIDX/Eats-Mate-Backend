package com.swidx.userservice.controller;

import com.swidx.userservice.feign.client.FeignJwtValidationService;
import com.swidx.userservice.service.LikeService;
import com.swidx.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user-service/user")
public class LikeController {
    private final JwtUtil jwtUtil;
    // private final FeignJwtValidationService feignJwtValidationService;
    private final LikeService likeService;

    @GetMapping("/like/{place_id}")
    public boolean checkUserLike(@RequestHeader("Authorization") String authorization,
                                 @PathVariable("place_id") Long placeId) {
        String accessToken = authorization.replace("Bearer ", "");

        // if (!feignJwtValidationService.check(accessToken)) {
        if (!jwtUtil.validateToken(accessToken)) {
            throw new java.lang.RuntimeException("*** LikeController: JWT Check Fail ***");
        }

        String email = jwtUtil.getTokenOwner(accessToken);
        return likeService.checkUserLike(email, placeId);
    }

    @PutMapping("/like/{place_id}")
    public void addUserLike(@RequestHeader("Authorization") String authorization,
                            @PathVariable("place_id") Long placeId) {
        String accessToken = authorization.replace("Bearer ", "");

        if (!jwtUtil.validateToken(accessToken)) {
            throw new java.lang.RuntimeException("*** LikeController: JWT Check Fail ***");
        }

        String email = jwtUtil.getTokenOwner(accessToken);
        likeService.addUserLike(email, placeId);
    }

    @DeleteMapping("/like/{place_id}")
    public void deleteUserLike(@RequestHeader("Authorization") String authorization,
                               @PathVariable("place_id") Long placeId) {
        String accessToken = authorization.replace("Bearer ", "");

        if (!jwtUtil.validateToken(accessToken)) {
            throw new java.lang.RuntimeException("*** LikeController: JWT Check Fail ***");
        }

        String email = jwtUtil.getTokenOwner(accessToken);
        likeService.deleteUserLike(email, placeId);
    }
}
