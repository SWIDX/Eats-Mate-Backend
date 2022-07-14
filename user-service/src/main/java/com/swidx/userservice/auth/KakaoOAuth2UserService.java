package com.swidx.userservice.auth;

import com.swidx.userservice.dto.KakaoUserDto;
import com.swidx.userservice.pojo.AccessToken;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoOAuth2UserService {

    public KakaoUserDto getUserInfo(@RequestParam AccessToken accessToken) {
        System.out.println("Received Access Token: " + accessToken.getData());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken.getData());
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        try {
            ResponseEntity<String> response = rt.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.POST,
                    kakaoProfileRequest,
                    String.class
            );
            String responseBody = response.getBody();
            // System.out.println(responseBody);

            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

                String id, nickname, profile_image, email;
                id = jsonObject.get("id").toString();
                JSONObject kakaoAccount = (JSONObject) jsonObject.get("kakao_account");
                email = kakaoAccount.get("email").toString();
                JSONObject profile = (JSONObject) kakaoAccount.get("profile");
                nickname = profile.get("nickname").toString();
                profile_image = profile.get("profile_image_url").toString();

                System.out.println("id: " + id);
                System.out.println("nickname: " + nickname);
                System.out.println("email: " + email);
                System.out.println("profile_image: " + profile_image);

                return new KakaoUserDto(id, nickname, email, profile_image);
            } catch (ParseException e) {
                System.out.println("\n*** KakaoOAuth2UserService: Resource Parsing Fail ***");
                System.out.println(e);
            }

        } catch (HttpStatusCodeException e) {
            System.out.println("\n*** KakaoOAuth2UserService: Kakao Auth Fail ***");
            System.out.println(e.getRawStatusCode());
            System.out.println(e.getResponseHeaders());
            // System.out.println(e.getResponseBodyAsString());
            // Reason for the null body: https://github.com/spring-projects/spring-framework/issues/14004
            // 시간 남으면 팩토리 구현해서 처리하기
        }
        
        // 나중에 @ExceptionHandler로 처리하기
        return null;
    }

}
