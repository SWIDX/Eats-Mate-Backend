package com.swidx.userservice.auth;

import com.swidx.userservice.dto.KakaoUserDto;
import com.swidx.userservice.dto.LoginResponseDto;
import com.swidx.userservice.pojo.AccessToken;
import com.swidx.userservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user-service/auth")
public class AuthController {
    private final KakaoOAuth2UserService kakaoService;
    private final LoginService loginService;

    @PostMapping("/kakao")
    public LoginResponseDto kakaoLogin(@RequestBody AccessToken accessToken) {
        KakaoUserDto userinfo = kakaoService.getUserInfo(accessToken);
        if (userinfo == null) return new LoginResponseDto("err", "", "", "", "");
        return loginService.registerUser(userinfo);
    }
}
