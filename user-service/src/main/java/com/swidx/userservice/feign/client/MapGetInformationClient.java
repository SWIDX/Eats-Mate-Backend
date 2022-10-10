package com.swidx.userservice.feign.client;

import com.swidx.userservice.dto.FeignInformationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="map-service", contextId = "MapGetInformationClient")
public interface MapGetInformationClient {

    @GetMapping("map-service/getRestInfo")
    FeignInformationDto getRestInfo(@RequestParam("id") Integer id);
}
