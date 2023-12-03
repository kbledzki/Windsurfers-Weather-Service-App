package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherServiceTest {

    private WeatherService weatherService;

    @BeforeEach
    void beforeEach() {
        weatherService = new WeatherService(new WeatherClient(), new DataService());
    }

    //    @Test
//    void getWeatherList2() {
//        weatherService.getWeatherListv2();
//    }
    @Test
    void getWeatherList() {
        weatherService.getBestLocationToSurf("2023-12-10");
    }
}