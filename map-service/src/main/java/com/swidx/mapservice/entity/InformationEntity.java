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
    private String type;

    @Column
    private String address;

    @Column(precision = 50,scale = 30)
    private BigDecimal lat;

    @Column(precision = 50,scale = 30)
    private BigDecimal lng;

    public InformationEntity(String name,String type,String address,BigDecimal lat,BigDecimal lng){
        this.name = name;
        this.type = type;
        this.address = address;
        this.lng = lng;
        this.lat = lat;
    }

}
