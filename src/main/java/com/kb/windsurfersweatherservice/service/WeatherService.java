package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.model.CityName;
import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;
    private static final int MIN_WIND_SPEED = 5;
    private static final int MAX_WIND_SPEED = 18;
    private static final int MIN_TEMPERATURE = 5;
    private static final int MAX_TEMPERATURE = 35;
    private static final int MULTIPLIER = 3;


    public Weather getBestLocationToSurf() {
        String bestSurfingLocation = calculateBestSurfingLocation();
        return weatherClient.getWeatherForCity(bestSurfingLocation);
    }

    private List<Weather> getWeatherForecastForAllCities() {
        List<Weather> weatherCityList = new ArrayList<>();

        for (CityName cityName : CityName.values()) {
            Weather weatherForCity = weatherClient.getWeatherForCity(cityName.toString());
            weatherCityList.add(weatherForCity);
        }
        return validSurfingCondition(weatherCityList);
    }

    private List<Weather> validSurfingCondition(List<Weather> weatherCityList) {
        return weatherCityList
                .stream()
                .filter(weather -> weather.getTemperature() >= MIN_TEMPERATURE)
                .filter(weather -> weather.getTemperature() <= MAX_TEMPERATURE)
                .filter(weather -> weather.getWindSpeed() >= MIN_WIND_SPEED)
                .filter(weather -> weather.getWindSpeed() <= MAX_WIND_SPEED)
                .collect(Collectors.toList());
    }

    private String calculateBestSurfingLocation() {
        List<Weather> weatherForecastForAllCities = getWeatherForecastForAllCities();
        Map<String, Float> bestCodntionMap = new HashMap<>();

        weatherForecastForAllCities.forEach(weather -> {
            float value = weather.getTemperature() + MULTIPLIER * weather.getWindSpeed();
            bestCodntionMap.put(weather.getCityName(), value);
        });
        return Collections.max(bestCodntionMap.keySet());
    }
}
