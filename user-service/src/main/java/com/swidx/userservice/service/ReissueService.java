package com.swidx.userservice.service;

import com.swidx.userservice.dto.ReissueResponseDto;
import com.swidx.userservice.redis.Token;
import com.swidx.userservice.redis.TokenRedisRepository;
import com.swidx.userservice.util.CookieUtil;
import com.swidx.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReissueService {
    private final JwtUtil jwtUtil;
    private final TokenRedisRepository redis;
    private final CookieUtil cookieUtil;

    public ResponseEntity<ReissueResponseDto> reissueJWT(String rt) {
        Token refreshToken = redis.findById(rt).orElseThrow(
                () -> new RuntimeException("\n*** ReissueService: Invalid Refresh Token ***")
        );
        String id = refreshToken.getId();

        String newAccessToken = jwtUtil.generateAccessToken(id, 1000L * 60 * 30); // 30 min
        String newRefreshToken = jwtUtil.generateRefreshToken(1000L * 60 * 60 * 6); // 6 hr

        // redis 업데이트
        redis.deleteById(rt); // invalidate previous token
        redis.save(new Token(newRefreshToken, id)); // String -> Token Entity

        ReissueResponseDto reissueBody = new ReissueResponseDto(
                newAccessToken,
                jwtUtil.getTokenExpirationTime(newAccessToken)
        );

        // 헤더에 들어감
        // maxAge는 초단위이고, 카운트다운 같은 개념이므로 JWT exp time과 혼동하면 안됨
        Long refreshMaxAge = 60L * 60 * 6;
        ResponseCookie reissueCookie = cookieUtil.generateRefreshTokenCookie(newRefreshToken, refreshMaxAge);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, reissueCookie.toString())
                .body(reissueBody);
    }

}
