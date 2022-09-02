package com.swidx.mapservice.service;

import com.swidx.mapservice.entity.InformationEntity;

import java.util.List;
import java.util.Optional;

public interface InformationService {
    public List getLikeInformation(String text);
    public Optional getInformation(Integer id);
}
