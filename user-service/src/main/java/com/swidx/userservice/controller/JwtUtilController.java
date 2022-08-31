package com.swidx.userservice.controller;

import com.swidx.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("user-service/jwt")
public class JwtUtilController {
    private final JwtUtil jwtUtil;

    @PostMapping("/email")
    String getUserEmail(@RequestBody String accessToken) {
        return jwtUtil.getTokenOwner(accessToken);
    }
}
