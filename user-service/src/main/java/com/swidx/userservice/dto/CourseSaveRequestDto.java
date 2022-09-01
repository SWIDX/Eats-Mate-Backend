package com.swidx.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
public class CourseSaveRequestDto {
    private String title;
    private List<String> placeNameList;
    private List<String> placeAddressList;
    private List<String> distanceList;

    @Builder
    public CourseSaveRequestDto(String title, List<String> placeNameList, List<String> placeAddressList, List<String> distanceList) {
        this.title = title;
        this.placeNameList = placeNameList;
        this.placeAddressList = placeAddressList;
        this.distanceList = distanceList;
    }
}