package com.swidx.userservice.service;

import com.swidx.userservice.domain.user.User;
import com.swidx.userservice.domain.user.UserRepository;
import com.swidx.userservice.dto.KakaoUserDto;
import com.swidx.userservice.dto.LoginResponseDto;
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

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class LoginService {
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final TokenRedisRepository redis;
    private final UserRepository db;
    boolean firstRegister = false;

    public ResponseEntity<LoginResponseDto> registerUser(KakaoUserDto userinfo) {
        // jwt 발급
        String id = userinfo.getId();
        String name = userinfo.getName();
        String profileImageUrl = userinfo.getProfileImageUrl();
        String accessToken = jwtUtil.generateAccessToken(id, 1000L * 60 * 30); // 30 min
        String refreshToken = jwtUtil.generateRefreshToken(1000L * 60 * 60 * 6); // 6 hr

        // redis 등록
        redis.save(new Token(refreshToken, id)); // String -> Token Entity

        // 신규: mariadb 등록
        User entity = db.findById(id).orElseGet( // orElse()는 empty 여부 관계 없이 인자 내 함수를 실행하므로 orElseGet() 사용
                () -> addUserdata(userinfo)
        );

        if(!firstRegister){
            System.out.println("\n*** LoginService: Registered User ***");
        }

        // 변동: mariadb 업데이트
        if (!name.equals(entity.getName()) || !profileImageUrl.equals(entity.getProfileImageUrl())) {
            System.out.println("\n*** LoginService: Update User ***");
            entity.update(userinfo);
        }

        LoginResponseDto loginbody = new LoginResponseDto(
                name,
                userinfo.getEmail(),
                profileImageUrl,
                accessToken,
                jwtUtil.getTokenExpirationTime(accessToken)
        );

        // 헤더에 들어감
        Long refreshMaxAge = 60L * 60 * 6;
        ResponseCookie loginCookie = cookieUtil.generateRefreshTokenCookie(refreshToken, refreshMaxAge);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, loginCookie.toString())
                .body(loginbody);
    }

    public ResponseEntity<LoginResponseDto> getPrimaryToken(KakaoUserDto userinfo, String rt) {
        System.out.println("\n*** LoginService: Secondary User Login ***");
        String id = userinfo.getId();
        String name = userinfo.getName();
        String profileImageUrl = userinfo.getProfileImageUrl();
        String accessToken = jwtUtil.generateAccessToken(id, 1000L * 60 * 30); // 30 min
        Token refreshToken = redis.findById(rt).orElseThrow(
                () -> new IllegalArgumentException("\n*** LoginService(Secondary): Invalid Refresh Token ***")
        );

        // mariadb에 등록된 사용자 정보 가져오기
        User entity = db.findById(id).orElseThrow(
                () -> new IllegalArgumentException("\n*** LoginService(Secondary): Invalid User ID ***")
        );

        // 변동: mariadb 업데이트
        if (!name.equals(entity.getName()) || !profileImageUrl.equals(entity.getProfileImageUrl())) {
            System.out.println("\n*** LoginService(Secondary): Update User ***");
            db.save(new User(userinfo));
        }

        LoginResponseDto loginbody = new LoginResponseDto(
                name,
                userinfo.getEmail(),
                profileImageUrl,
                accessToken,
                jwtUtil.getTokenExpirationTime(accessToken)
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body(loginbody); // no header
    }

    public ResponseEntity<Void> deleteToken(String rt) {
        Optional<Token> refreshToken = redis.findById(rt);
        if (refreshToken.isPresent()) {
            redis.deleteById(rt);
        }

        ResponseCookie logoutCookie = cookieUtil.expireRefreshTokenCookie();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, logoutCookie.toString())
                .body(null);
    }

    private User addUserdata(KakaoUserDto userinfo) {
        System.out.println("\n*** LoginService: New User ***");
        firstRegister = true;
        return db.save(new User(userinfo));
    }
}
