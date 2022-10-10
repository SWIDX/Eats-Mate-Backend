package com.swidx.reviewservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class FeignRestInformationDto {
    private Integer id;
    private String name;
    private String gubun;
    private String gugun;
    private String address;
    private BigDecimal lat;
    private BigDecimal lng;
    private String cntct;
    private String homepage_url;
    private String usage_of_week_and_time;
    private ArrayList<String> image;
}
