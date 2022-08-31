package com.swidx.userservice.controller;

import com.swidx.userservice.dto.CourseSaveRequestDto;
import com.swidx.userservice.dto.CourseResponseDto;
import com.swidx.userservice.service.CourseService;
import com.swidx.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user-service/user")
public class CourseController {
    private final JwtUtil jwtUtil;
    private final CourseService courseService;

    @GetMapping("/course/all")
    public ResponseEntity<List<CourseResponseDto>> getUserCourse(@RequestHeader("Authorization") String authorization) {
        String accessToken = authorization.replace("Bearer ", "");

        if (!jwtUtil.validateToken(accessToken)) {
            System.out.println("*** CourseController: JWT Check Fail ***");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //throw new RuntimeException("*** CourseController: JWT Check Fail ***");
        }

        String email = jwtUtil.getTokenOwner(accessToken);
        return new ResponseEntity<List<CourseResponseDto>>(courseService.getAllUserCourse(email), HttpStatus.OK);
    }

    @PostMapping("/course")
    public ResponseEntity<Long> addUserCourse(@RequestHeader("Authorization") String authorization,
                            @RequestBody CourseSaveRequestDto courseInfo) {
        String accessToken = authorization.replace("Bearer ", "");

        if (!jwtUtil.validateToken(accessToken)) {
            System.out.println("*** CourseController: JWT Check Fail ***");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String email = jwtUtil.getTokenOwner(accessToken);
        courseService.saveUserCourse(email, courseInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/course/{course_id}")
    public ResponseEntity<Long> deleteUserCourse(@RequestHeader("Authorization") String authorization,
                               @PathVariable("course_id") Long courseId) {
        String accessToken = authorization.replace("Bearer ", "");

        if (!jwtUtil.validateToken(accessToken)) {
            System.out.println("*** CourseController: JWT Check Fail ***");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String email = jwtUtil.getTokenOwner(accessToken);
        courseService.deleteUserCourse(email, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
