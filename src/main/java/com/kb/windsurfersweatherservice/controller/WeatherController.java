package com.kb.windsurfersweatherservice.controller;

import com.kb.windsurfersweatherservice.service.WeatherService;
import com.kb.windsurfersweatherservice.webclient.weather.dto.WeatherbitForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherbitForecastDto getWeather() {
        return weatherService.getWeather();
    }

}
