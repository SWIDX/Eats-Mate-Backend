package com.swidx.mapservice.Repository;


import com.swidx.mapservice.entity.InformationEntity;
import com.swidx.mapservice.entity.TourId;
import com.swidx.mapservice.entity.TourInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface TourInformationRepository extends JpaRepository<TourInformationEntity, TourId> {
    TourInformationEntity findByTitle(String title);

    //1km = 10
    @Query(value="SELECT *,(6371*acos(cos(radians(?1))*cos(radians(lat))*cos(radians(lng) - radians(?2)) + sin(radians(?1))*sin(radians(lat)))) AS distance FROM tour_information HAVING distance <= ?3*10 and distance > 0 ORDER BY distance limit 0,10",nativeQuery = true)
    List<TourInformationEntity> findByNearby(double target_lat, double target_lng, double dist);
}
