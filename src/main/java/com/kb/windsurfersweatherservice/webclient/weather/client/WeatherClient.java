package com.kb.windsurfersweatherservice.webclient.weather.client;

import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.webclient.weather.dto.WeatherbitForecastDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class WeatherClient {

    private static final String WEATHER_URL = "https://api.weatherbit.io/v2.0/forecast/daily";
    private static final String API_KEY = "66964f620c5d4dd9b43244e2021fdf6a";
    private final RestTemplate restTemplate = new RestTemplate();

    public Weather getWeatherForCity(String city, long day) {
        WeatherbitForecastDto response = restTemplate.getForObject(WEATHER_URL + "?city={city}&key={apiKey}",
                WeatherbitForecastDto.class,
                city,
                API_KEY);
        return Weather.builder()
                .cityName(response.getCity_name())
                .lat(response.getLat())
                .lon(response.getLon())
                .windSpeed(Arrays.stream(response.getData()).toList().get((int) day).getWind_spd())
                .temperature(Arrays.stream(response.getData()).toList().get((int) day).getTemp())
                .build();
    }
}