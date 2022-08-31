package com.swidx.mapservice.controller;


import com.swidx.mapservice.Repository.TourInformationRepository;
import com.swidx.mapservice.entity.InformationEntity;
import com.swidx.mapservice.entity.TourId;
import com.swidx.mapservice.entity.TourInformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map-service")

public class TourInformationController {
    private final TourInformationRepository tourInformationRepository;

    @GetMapping("tour-information/findByName")
    public TourInformationEntity findByName(@RequestParam("name") String name){

        TourInformationEntity result = tourInformationRepository.findByName(name);
        return result;

    }

    @GetMapping("tour-information/findByName/{name}")
    public List<TourInformationEntity> findByNameLike(@PathVariable String name){
        List<TourInformationEntity> result = tourInformationRepository.findByNameLike("%"+name+"%");
        if(result.isEmpty()){
            return null;
        } else {
            return result;
        }
    }

    // findByGubun


}
