package com.swidx.userservice.domain.course;

import com.swidx.userservice.dto.CourseSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "user_course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String placeNameList;

    @Column(nullable = false)
    private String placeAddressList;

    @Column(nullable = false)
    private String distanceList;

    public Course(String email, CourseSaveRequestDto courseInfo) {
        this.email = email;
        this.title = courseInfo.getTitle();
        this.placeNameList = String.join(",", courseInfo.getPlaceNameList());
        this.placeAddressList = String.join(",", courseInfo.getPlaceAddressList());
        this.distanceList = String.join(",", courseInfo.getDistanceList());
    }
}
