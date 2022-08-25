package com.swidx.mapservice.repository;

import com.swidx.mapservice.Repository.TourInformationRepository;
import com.swidx.mapservice.entity.TourInformationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TourInformationRepositoryTest {

    @Autowired
    TourInformationRepository tourInformationRepository;

    @Test
    public void title로_정보찾기(){
        String title = "구암공원";

        TourInformationEntity response = tourInformationRepository.findByTitle(title);
        System.out.println(response.getTourId());
        System.out.println(response.getTitle());
        System.out.println(response.getGubun());

    }

    @Test
    public void 가장근처에있는_관광지_찾기(){
        double lat = 37.55741617087040;
        double lng = 126.93751998804600;
        double dist = 1.0;

        List<TourInformationEntity> tourInformationEntityList = tourInformationRepository.findByNearby(lat,lng,dist);

        for(TourInformationEntity info : tourInformationEntityList){
            System.out.println(info.getTourId());
            System.out.println(info.getTitle());
        }
    }
}
