package com.swidx.mapservice.service;

import com.swidx.mapservice.dto.InformationResponseDto;
import com.swidx.mapservice.entity.InformationEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InformationService {
    public List getLikeInformation(String text);
    public InformationResponseDto getSearchName(String text);
    public InformationResponseDto getInformation(Integer id);
}
