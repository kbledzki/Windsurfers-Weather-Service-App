package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import com.kb.windsurfersweatherservice.webclient.weather.dto.WeatherbitForecastDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherbitForecastDto getWeather() {
        return weatherClient.getWeatherForCity("Sopot");
    }
}
