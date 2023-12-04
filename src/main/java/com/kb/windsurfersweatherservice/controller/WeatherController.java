package com.kb.windsurfersweatherservice.controller;

import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Weather getWeather(@PathVariable String date) {
        return weatherService.getBestLocationToSurf(date);
    }
}
