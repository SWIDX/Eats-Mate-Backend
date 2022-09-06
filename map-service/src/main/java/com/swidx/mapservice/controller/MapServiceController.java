package com.swidx.mapservice.controller;

import com.swidx.mapservice.entity.InformationEntity;
import com.swidx.mapservice.entity.TourInformationEntity;
import com.swidx.mapservice.entity.UnionInterface;
import com.swidx.mapservice.service.InformationService;
import com.swidx.mapservice.service.TourInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

@RequestMapping("/map-service")

public class MapServiceController {
    private final InformationService informationService;
    private final TourInformationService tourInformationService;


    @GetMapping("getAllData")
    public List<UnionInterface> getAllData(@RequestParam("keyword") String keyword){
        List<UnionInterface> res =tourInformationService.getAllUnionInformation(keyword);
        if(!res.isEmpty()){
            return res;
        }else{
            return null;
        }
    }

    @GetMapping("getRestInfo")
    public Optional getRestInfo(@RequestParam("id") Integer id){
        Optional res = informationService.getInformation(id);
        if(!res.isEmpty()){
            return res;
        }else{
            return null;
        }
    }

    @GetMapping("getTourInfo")
    public TourInformationEntity getTourInfo(@RequestParam("id") Integer id){
        //request id is contents id
        TourInformationEntity res = tourInformationService.getTourInformation(id);
        if(res.getName()!=""){
            return res;
        }else{
            return null;
        }
    }

    @GetMapping("findByName")
    public ResponseEntity<InformationEntity> findByName(@RequestParam("name") String name){
        InformationEntity res = informationService.getSearchName(name);
        var status = res == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity(res,status);
    }

}
