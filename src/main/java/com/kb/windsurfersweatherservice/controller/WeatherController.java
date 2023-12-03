package com.kb.windsurfersweatherservice.controller;

import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather/{date}")
    public Weather getWeather(@PathVariable String date) {
        return weatherService.getBestLocationToSurf(date);
    }
}
