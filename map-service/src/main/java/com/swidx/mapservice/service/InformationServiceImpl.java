package com.swidx.mapservice.service;

import com.swidx.mapservice.Repository.InformationRepository;
import com.swidx.mapservice.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InformationServiceImpl implements InformationService{
    @Autowired
    private final InformationRepository db;

    public List<InformationEntity> getLikeInformation(String text){
        List<InformationEntity> listOfInfo;
        listOfInfo = db.getSearchRestInfo(text);
        return  listOfInfo;

    }

    public Optional getInformation(Integer id){
        Optional res = db.findById(id);
        return res;
    }
}
