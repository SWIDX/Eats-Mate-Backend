package com.swidx.mapservice.controller;

import com.swidx.mapservice.Repository.InformationRepository;
import com.swidx.mapservice.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map-service")
public class InformationController {
    private final InformationRepository informationRepository;

    @GetMapping("information/get_all")
    public List<InformationEntity> findAllMember(){
        return informationRepository.findAll();
    }

    @GetMapping("information/findById/{id}")
    public Optional<InformationEntity> findById(@PathVariable Integer id){
        return informationRepository.findById(id);
    }

    @GetMapping("information/findByName/{name}")
    public InformationEntity findByNameLike(@PathVariable String name){
         List<InformationEntity> result = informationRepository.findByNameLike("%"+name+"%");
         if(result.isEmpty()){
             return null;
         }else {
             return result.get(0);
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
