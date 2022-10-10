package com.swidx.mapservice.service;

import com.swidx.mapservice.Repository.InformationRepository;
import com.swidx.mapservice.dto.InformationResponseDto;
import com.swidx.mapservice.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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


    public InformationResponseDto getInformation(Integer id){
        Optional<InformationEntity> data = db.findById(id);
        ArrayList<String> image = new ArrayList<String>();

        if(data.get().getImage()!=null){
            String[] temp = data.get().getImage().split(",");
            image = new ArrayList<String>(Arrays.asList(temp));
        }

        InformationResponseDto information = InformationResponseDto.builder()
                .entity(data)
                .image(image)
                .build();

        return information;
    }



    public InformationResponseDto getSearchName(String name){
        Optional<InformationEntity> data = db.findByName(name);
        ArrayList<String> image = new ArrayList<String>();

        if(data.get().getImage()!=null){
            String[] temp = data.get().getImage().split(",");
            image = new ArrayList<String>(Arrays.asList(temp));
        }

        InformationResponseDto information = InformationResponseDto.builder()
                .entity(data)
                .image(image)
                .build();

        return information;

    }

}
