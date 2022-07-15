package com.swidx.mapservice.controller;

import com.swidx.mapservice.Repository.InformationRepository;
import com.swidx.mapservice.entity.InformationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map-service")
public class InformationController {
    private final InformationRepository informationRepository;

    @GetMapping("information")
    public List<InformationEntity> findAllMember(){
        return informationRepository.findAll();
    }
}
