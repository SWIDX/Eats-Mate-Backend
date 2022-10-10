package com.swidx.reviewservice.feign.client;

import com.swidx.reviewservice.controller.dto.FeignRestInformationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="map-service", contextId = "GetRestInfoClient")
public interface GetRestInfoClient {

    @GetMapping("map-service/findByName")
    FeignRestInformationDto getRestInfo(@RequestParam("name") String name);
}
