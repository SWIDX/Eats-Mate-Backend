package com.swidx.userservice.controller;

import com.swidx.userservice.domain.user.User;
import com.swidx.userservice.domain.user.UserRepository;
import com.swidx.userservice.dto.FeignUserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("user-service/user")
public class InfoController {
    private final UserRepository userRepository;

    @PostMapping("/info")
    public FeignUserInfoResponseDto getUserNameAndProfileImgUrl(@RequestBody String email) {
        Optional<User> user = userRepository.findById(email);
        if (user.isPresent()) {
            return new FeignUserInfoResponseDto(user.get());
        }
        else {
            throw new RuntimeException("*** InfoController: User Not Found ***");
        }
    }
}
