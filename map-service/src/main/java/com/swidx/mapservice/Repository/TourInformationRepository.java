package com.swidx.mapservice.Repository;

import com.swidx.mapservice.entity.TourId;
import com.swidx.mapservice.entity.TourInformationEntity;
import com.swidx.mapservice.entity.UnionInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface TourInformationRepository extends JpaRepository<TourInformationEntity, TourId> {
    TourInformationEntity findByName(String name);

    //1km = 10
    @Query(value="SELECT *,(6371*acos(cos(radians(?1))*cos(radians(lat))*cos(radians(lng) - radians(?2)) + sin(radians(?1))*sin(radians(lat)))) AS distance FROM tour_information HAVING distance <= ?3*10 and distance > 0 ORDER BY distance limit 0,10",nativeQuery = true)
    List<TourInformationEntity> findByNearby(double target_lat, double target_lng, double dist);

    List<TourInformationEntity> findByNameLike(String name);

    @Query(value = "select *,'관광지' as type from tour_information where (name like %:text% or address like %:text% )",nativeQuery = true)
    List<TourInformationEntity> getSearchTourInfo(String text);

    @Query(value ="select id, name, gubun, lat, lng, address, '음식점' as type from map_service.information " +
            "where (name like %:text% or address like %:text% or gugun like %:text%) " +
            "union select content_id as id, name, gubun, lat, lng, address, '여행지' as type " +
            "from map_service.tour_information where (name like %:text% or address " +
            "like %:text% ) ORDER BY name asc ", countQuery = "select id, name, gubun, address, '음식점' as type from map_service.information where (name like %:text% or address like %:text% or gugun like %:text%) union select content_id as id, name, gubun, address, '여행지' as type from map_service.tour_information where (name like %:text% or address like %:text% ) ORDER BY name asc ",nativeQuery = true)
    List<UnionInterface> getSearchUnionInformation(String text);

    @Query(value="select * from tour_information where content_id = :contentsId",nativeQuery = true)
    TourInformationEntity getSearchContentsId(Integer contentsId);
}
