package com.swidx.mapservice.service;

import com.swidx.mapservice.Repository.InformationRepository;
import com.swidx.mapservice.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class InformationServiceImpl implements InformationService{
    @Autowired
    private final InformationRepository db;

    public List<InformationEntity> getLikeInformation(String text){
        List<InformationEntity> listOfInfo;
        listOfInfo = db.getSearchRestInfo(text);
        return  listOfInfo;

    }

    public Optional<InformationEntity> getInformation(Integer id){
        Optional<InformationEntity> res = db.findById(id);
        return res;
    }

    public InformationEntity getSearchName(String name){
        try{
            InformationEntity res = db.findByName(name);
            return res;

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

}
