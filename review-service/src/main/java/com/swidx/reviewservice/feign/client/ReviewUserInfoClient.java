package com.swidx.reviewservice.feign.client;

import com.swidx.reviewservice.controller.dto.FeignUserInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", contextId = "ReviewUserInfoClient")
public interface ReviewUserInfoClient {

    @PostMapping("user-service/user/info")
    // user-service/user/info 에서 user-service 빼면 404 error 이므로 주의
    FeignUserInfoResponseDto getUserNameAndProfileImgUrl(@RequestBody String email);

}
