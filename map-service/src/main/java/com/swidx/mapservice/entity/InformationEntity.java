package com.swidx.mapservice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity(name="information")
public class InformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String gubun;

    @Column
    private String gugun;

    @Column
    private String address;

    @Column(precision = 16,scale = 14)
    private BigDecimal lat;

    @Column(precision = 17,scale = 14)
    private BigDecimal lng;

    @Column
    private String cntct;

    @Column
    private String homepage_url;

    @Column
    private String usage_of_week_and_time;



    public InformationEntity(String name,String gubun,String address,BigDecimal lat,
                             BigDecimal lng,String cntct,String gugun,String homepage_url,
                             String usage_of_week_and_time){
        this.name = name;
        this.gubun = gubun;
        this.address = address;
        this.lng = lng;
        this.lat = lat;
        this.cntct = cntct;
        this.gugun = gugun;
        this.homepage_url = homepage_url;
        this.usage_of_week_and_time = usage_of_week_and_time;
    }

}
