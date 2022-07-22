package com.swidx.userservice.auth;

import com.swidx.userservice.dto.KakaoUserDto;
import com.swidx.userservice.dto.LoginResponseDto;
import com.swidx.userservice.dto.ReissueResponseDto;
import com.swidx.userservice.pojo.AccessToken;
import com.swidx.userservice.service.LoginService;
import com.swidx.userservice.service.ReissueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("user-service/auth")
public class AuthController {
    private final KakaoOAuth2UserService kakaoService;
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/kakao")
    public ResponseEntity<LoginResponseDto> kakaoLogin(@CookieValue(value = "refreshToken", defaultValue = "") String refreshToken, @RequestBody AccessToken accessToken) throws Exception {
        boolean primary = Objects.equals(refreshToken, "");
        KakaoUserDto userinfo = kakaoService.getUserInfo(accessToken);

        if (userinfo == null) // 차후 수정하기
            throw new Exception("\n*** AuthController: 카카오 서버로부터 사용자 정보를 가져오는데 실패했습니다. ***");

        if (primary) // 최초 로그인
            return loginService.registerUser(userinfo);
        else // 다중 클라이언트 대응
            return loginService.getPrimaryToken(userinfo, refreshToken);
    }

    @GetMapping("/reissue")
    public ResponseEntity<ReissueResponseDto> reissueJWT(@CookieValue("refreshToken") String refreshToken) {
        return reissueService.reissueJWT(refreshToken);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> deleteToken(@CookieValue("refreshToken") String refreshToken) {
        return loginService.deleteToken(refreshToken);
    }
}
