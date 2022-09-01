package com.swidx.userservice.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<List<Course>> findAllByEmail(String email);
    void deleteByEmailAndCourseId(String email, Long courseId);
}
