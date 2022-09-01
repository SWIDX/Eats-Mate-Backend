package com.swidx.userservice.dto;

import com.swidx.userservice.domain.course.Course;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class CourseResponseDto {
    private Long courseId;
    private String title;
    private List<String> placeNameList;
    private List<String> placeAddressList;
    private List<Long> distanceList;

    public CourseResponseDto(Course course) {
        this.courseId = course.getCourseId();
        this.title = course.getTitle();
        this.placeNameList = Arrays.asList(course.getPlaceNameList().split("\\s*,\\s*"));
        this.placeAddressList = Arrays.asList(course.getPlaceAddressList().split("\\s*,\\s*"));
        this.distanceList = Stream.of(course.getDistanceList().split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
