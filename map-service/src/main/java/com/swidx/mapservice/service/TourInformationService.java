package com.swidx.mapservice.service;

import com.swidx.mapservice.entity.TourInformationEntity;

import java.util.List;

public interface TourInformationService {
    public TourInformationEntity getTourInformation(Integer contentsid);
    public List getLikeTourInformation(String text);
    public List getAllUnionInformation(String text);
}
