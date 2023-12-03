package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.exceptions.WeatherAppException;
import com.kb.windsurfersweatherservice.exceptions.WeatherError;
import com.kb.windsurfersweatherservice.model.City;
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
    private final DataService dataService;
    private static final int MIN_WIND_SPEED = 5;
    private static final int MAX_WIND_SPEED = 18;
    private static final int MIN_TEMPERATURE = 5;
    private static final int MAX_TEMPERATURE = 35;
    private static final int MULTIPLIER = 3;

    public Weather getBestLocationToSurf(String date) {
        long day = dataService.calculateDayToCheckWeather(date);
        String bestSurfingLocation = calculateBestSurfingLocation(day);
        return weatherClient.getWeatherForCity(bestSurfingLocation, day);
    }

    private List<Weather> getWeatherForecastForAllCities(long day) {
        List<Weather> weatherCityList = new ArrayList<>();

        for (City city : City.values()) {
            Weather weatherForCity = weatherClient.getWeatherForCity(city.getName(), day);
            System.out.println(weatherForCity);
            weatherCityList.add(weatherForCity);
        }
        return weatherCityList;
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

    private String calculateBestSurfingLocation(long day) {
        Map<Double, String> bestCodntionMap = new HashMap<>();
        List<Weather> weatherCityList = validSurfingCondition(getWeatherForecastForAllCities(day));
        checkIfThereAreAnyGoodConditions(weatherCityList);

        weatherCityList.forEach(weather -> {
            double value = weather.getTemperature() + MULTIPLIER * weather.getWindSpeed();
            bestCodntionMap.put(value, weather.getCityName());
        });

        Double bestFormulaValue = Collections.max(bestCodntionMap.keySet());
        return bestCodntionMap.get(bestFormulaValue);
    }

    private void checkIfThereAreAnyGoodConditions(List<Weather> weatherCityList) {
        if (weatherCityList.isEmpty()) {
            throw new WeatherAppException(WeatherError.BAD_WEATHER_CONDITIONS_FOR_ALL_CITIES);
        }
    }
}
