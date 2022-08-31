package com.swidx.userservice.service;

import com.swidx.userservice.domain.course.Course;
import com.swidx.userservice.domain.course.CourseRepository;
import com.swidx.userservice.dto.CourseSaveRequestDto;
import com.swidx.userservice.dto.CourseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository db;

    public List<CourseResponseDto> getAllUserCourse(String email) {
        Optional<List<Course>> list = db.findAllByEmail(email);
        List<CourseResponseDto> parsedList = new ArrayList<CourseResponseDto>();

        if(list.isPresent()) {
            for (Course cobj: list.get()) {
                parsedList.add(new CourseResponseDto(cobj));
            }
        } // else return empty list (no exception)

        return parsedList;
    }

    public void saveUserCourse(String email, CourseSaveRequestDto courseInfo) {
        db.save(new Course(email, courseInfo));
    }

    @Transactional
    public void deleteUserCourse(String email, Long courseId) {
        db.deleteByEmailAndCourseId(email, courseId);
    }
}
