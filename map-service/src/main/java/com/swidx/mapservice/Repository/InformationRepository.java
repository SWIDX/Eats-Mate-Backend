package com.swidx.mapservice.Repository;

import com.swidx.mapservice.entity.InformationEntity;
import com.swidx.mapservice.entity.TourInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface InformationRepository extends JpaRepository<InformationEntity,Integer>{

    List<InformationEntity> findByNameLike(String name);
    //1km = 10
    @Query(value="SELECT *,(6371*acos(cos(radians(?1))*cos(radians(lat))*cos(radians(lng) - radians(?2)) + sin(radians(?1))*sin(radians(lat)))) AS distance FROM information HAVING distance <= ?3*10 and distance > 0 ORDER BY distance limit 0,10",nativeQuery = true)
    List<InformationEntity> findByNearby(double target_lat,double target_lng,double dist);

    @Query(value = "select *,'음식점' as type from information where (name like %:text% or address like %:text% )",nativeQuery = true)
    List<InformationEntity> getSearchRestInfo(String text);
}
