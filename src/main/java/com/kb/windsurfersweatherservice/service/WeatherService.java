package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public Weather getWeather() {
        return weatherClient.getWeatherForCity("Sopot");
    }
}
