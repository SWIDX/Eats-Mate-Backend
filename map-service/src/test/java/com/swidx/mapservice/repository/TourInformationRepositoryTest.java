package com.swidx.mapservice.repository;

import com.swidx.mapservice.Repository.TourInformationRepository;
import com.swidx.mapservice.entity.TourInformationEntity;
import com.swidx.mapservice.entity.UnionInterface;
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
    public void title로_정보찾기() {
        String title = "구암공원";

        TourInformationEntity response = tourInformationRepository.findByName(title);
        System.out.println(response.getTourId());
        System.out.println(response.getName());
        System.out.println(response.getGubun());

    }

    @Test
    public void 가장근처에있는_관광지_찾기() {
        double lat = 37.55741617087040;
        double lng = 126.93751998804600;
        double dist = 1.0;

        List<TourInformationEntity> tourInformationEntityList = tourInformationRepository.findByNearby(lat, lng, dist);

        for (TourInformationEntity info : tourInformationEntityList) {
            System.out.println(info.getTourId());
            System.out.println(info.getName());
        }
    }

    @Test
    public void 모든정보_가져오기() {
        String title = "홍대";

        List<TourInformationEntity> response = tourInformationRepository.getSearchTourInfo(title);

        for (TourInformationEntity re : response) {
            System.out.println(re.getTourId());
            System.out.println(re.getName());
            System.out.println(re.getAddress());
        }


    }

    @Test
    public void 모든정보2() {
        String title = "홍대";

        List<UnionInterface> response = tourInformationRepository.getSearchUnionInformation(title);

        for (UnionInterface re : response) {
            System.out.println(re.getId());
            System.out.println(re.getName());
            System.out.println(re.getAddress());

        }
    }
}
