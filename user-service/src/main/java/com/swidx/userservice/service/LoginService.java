package com.swidx.userservice.service;

import com.swidx.userservice.domain.user.User;
import com.swidx.userservice.domain.user.UserRepository;
import com.swidx.userservice.dto.KakaoUserDto;
import com.swidx.userservice.dto.LoginResponseDto;
import com.swidx.userservice.redis.Token;
import com.swidx.userservice.redis.TokenRedisRepository;
import com.swidx.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LoginService {
    private final JwtUtil jwtUtil;
    private final TokenRedisRepository redis;
    private final UserRepository db;
    boolean firstRegister = false;

    public LoginResponseDto registerUser(KakaoUserDto userinfo) {
        // jwt 발급
        String id = userinfo.getId();
        String name = userinfo.getName();
        String profileImageUrl = userinfo.getProfileImageUrl();
        String accessToken = jwtUtil.generateAccessToken(id, 1000L * 60 * 30); // 30 min
        String refreshToken = jwtUtil.generateRefreshToken(1000L * 60 * 60 * 6); // 6 hr

        // redis 등록
        redis.save(new Token(refreshToken)); // String -> Token Entity

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

        return new LoginResponseDto(
                name,
                userinfo.getEmail(),
                profileImageUrl,
                accessToken,
                refreshToken
        );
    }

    private User addUserdata(KakaoUserDto userinfo) {
        System.out.println("\n*** LoginService: New User ***");
        firstRegister = true;
        return db.save(new User(userinfo));
    }
}
