package com.swidx.mapservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
public class TourId implements Serializable {

    @Column
    private Integer id;

    @Column(name="content_id")
    private Integer content_id;
}
