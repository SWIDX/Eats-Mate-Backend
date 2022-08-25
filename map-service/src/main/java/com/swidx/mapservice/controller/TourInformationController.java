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

    @GetMapping("tour-information/findByTitle")
    public TourInformationEntity findByTitle(@RequestParam("title") String title){

        TourInformationEntity result = tourInformationRepository.findByTitle(title);
        return result;

    }




}
