package com.swidx.mapservice.controller;

import com.swidx.mapservice.Repository.InformationRepository;
import com.swidx.mapservice.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map-service")

public class InformationController {
    private final InformationRepository informationRepository;

    @GetMapping("information/get_all")
    public List<InformationEntity> findAllInformation(){
        return informationRepository.findAll();
    }

    @GetMapping("information/findByName/{name}")
    public List<InformationEntity> findByNameLike(@PathVariable String name){
         List<InformationEntity> result = informationRepository.findByNameLike("%"+name+"%");
         if(result.isEmpty()){
             return null;
         }else {
             return result;
         }
    }

    @GetMapping("information/findByNearby")
    public List<InformationEntity> findByNearby(@RequestParam("lat") Double lat,
                                                @RequestParam("lng") Double lng,
                                                @RequestParam("dist") Double dist){
        List<InformationEntity> result = informationRepository.findByNearby(lat,lng,dist);
        return result;
    }


}
