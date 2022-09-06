package com.swidx.reviewservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", contextId = "JwtValidationClient")
public interface JwtValidationClient {

    @PostMapping("user-service/jwt/email")
    // user-service/jwt/email 에서 user-service 빼면 404 error 이므로 주의
    String getUserEmail(@RequestBody String accessToken);
}
