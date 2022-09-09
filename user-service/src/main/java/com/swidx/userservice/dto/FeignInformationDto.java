package com.swidx.userservice.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class FeignInformationDto {
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
}
