package com.swidx.mapservice.dto;

import com.swidx.mapservice.entity.InformationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;


@AllArgsConstructor
@Getter

public class InformationResponseDto {
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

    @Builder
    InformationResponseDto(Optional<InformationEntity> entity, ArrayList<String> image){


            this.id = entity.get().getId();
            this.name = entity.get().getName();
            this.gubun = entity.get().getGubun();
            this.address = entity.get().getAddress();
            this.lng = entity.get().getLng();
            this.lat = entity.get().getLat();
            this.cntct = entity.get().getCntct();
            this.gugun = entity.get().getGugun();
            this.homepage_url = entity.get().getHomepage_url();
            this.usage_of_week_and_time = entity.get().getUsage_of_week_and_time();
            this.image = image;

    }

}
