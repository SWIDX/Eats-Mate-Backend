package com.swidx.mapservice.controller;

import com.swidx.mapservice.controller.InformationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebAppConfiguration
@WebMvcTest(InformationController.class)
public class InformationControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("findbynearby controller test")
    void 근처_음식점_탐색() throws Exception{

        //given
        double lat = 37.55804954047840;
        double lng = 126.92188085007200;
        double dist = 1.5;


        //when

        //then
        mvc.perform((get("/map-service/information/findByNearby")
                .param("lat",Double.toString(lat))
                .param("lng",Double.toString(lng))
                .param("dist",Double.toString(dist))))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

    }

    @Test
    void 음식점_검색() throws Exception{
        //given
        String name = "홍대";

        mvc.perform((get("/map-service/information/findByName/")
                .param("name",name)))
                .andExpect(status().isOk())
                .andReturn();


    }
}
