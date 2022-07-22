package com.swidx.userservice.util;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public ResponseCookie generateRefreshTokenCookie(String refreshToken, Long refreshMaxAge) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .path("/")
                .sameSite("Strict")
                .httpOnly(true)
                .secure(false) // 프로덕션(https)에선 반드시 true로 설정하기
                .maxAge(refreshMaxAge)
                .build();
    }

    public ResponseCookie expireRefreshTokenCookie() {
        return ResponseCookie.from("refreshToken", "")
                .path("/")
                .sameSite("Strict")
                .httpOnly(true)
                .secure(false) // 프로덕션(https)에선 반드시 true로 설정하기
                .maxAge(0)
                .build();
    }
}
