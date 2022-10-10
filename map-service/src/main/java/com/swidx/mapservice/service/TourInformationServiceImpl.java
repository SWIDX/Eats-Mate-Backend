package com.swidx.mapservice.service;

import com.swidx.mapservice.Repository.TourInformationRepository;
import com.swidx.mapservice.entity.TourId;
import com.swidx.mapservice.entity.TourInformationEntity;
import com.swidx.mapservice.entity.UnionInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TourInformationServiceImpl implements TourInformationService{
    @Autowired
    private final TourInformationRepository db;

    @Override
    public List<TourInformationEntity> getLikeTourInformation(String text){
        List<TourInformationEntity>  listOfTourInfo ;
        listOfTourInfo = db.getSearchTourInfo(text);
        return listOfTourInfo;
    }

    @Override
    public List<UnionInterface> getAllUnionInformation(String text){
        List<UnionInterface> listOfAllInfo;
        listOfAllInfo = db.getSearchUnionInformation(text);

        return listOfAllInfo;
    }

    @Override
    public TourInformationEntity getTourInformation(Integer contentsid){
        TourInformationEntity tourInformation = db.getSearchContentsId(contentsid);
        return tourInformation;
    }



}
