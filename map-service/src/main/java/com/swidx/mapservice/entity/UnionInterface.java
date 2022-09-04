package com.swidx.mapservice.entity;

import java.math.BigDecimal;

public interface UnionInterface {
    Integer getId();
    String getName();
    String getGubun();
    BigDecimal getLat();
    BigDecimal getLng();
    String getAddress();
    String getType();
}
