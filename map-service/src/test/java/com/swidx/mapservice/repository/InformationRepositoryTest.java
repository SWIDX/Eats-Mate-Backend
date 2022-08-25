package com.swidx.mapservice.repository;


import com.swidx.mapservice.Repository.InformationRepository;
import com.swidx.mapservice.entity.InformationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest

//test informationRepository code
public class InformationRepositoryTest {

    @Autowired
    InformationRepository informationRepository;
    //dont worry this error
    //it works well

    @Test
    public void 근처_식당_찾기(){

        double lat = 37.55741617087040;
        double lng = 126.93751998804600;
        double dist = 1.0;
        //The coordinates that are central to the calculation of the radius

        List<InformationEntity> informationEntityList = informationRepository.findByNearby(
              lat,lng,dist
        );

        for(InformationEntity info : informationEntityList){
            System.out.println(info.getName());
        }
    }

    @Test
    public void 이름으로_식당_찾기(){
        String name = "홍대";

        List<InformationEntity> informationEntityList = informationRepository.findByNameLike(name);

        for(InformationEntity info : informationEntityList){
            System.out.println(info.getName()+":"+info.getGugun());
        }

    }}
