package com.swidx.mapservice.service;

import com.swidx.mapservice.entity.InformationEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InformationService {
    public List getLikeInformation(String text);
    public Optional<InformationEntity> getInformation(Integer id);
    public InformationEntity getSearchName(String text);
}
