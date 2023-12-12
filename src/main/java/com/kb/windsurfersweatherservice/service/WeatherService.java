package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.exceptions.WeatherAppException;
import com.kb.windsurfersweatherservice.exceptions.WeatherError;
import com.kb.windsurfersweatherservice.model.City;
import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.model.WeatherDto;
import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public WeatherDto getBestLocationToSurf(String date) {
        long day = dataService.calculateDayToCheckWeather(date);
        return mapToDto(calculateBestSurfingLocation(day));
    }

    private List<Weather> getWeatherForecastForAllCities(long day) {
        List<Weather> weatherCityList = new ArrayList<>();

        for (City city : City.values()) {
            Weather weatherForCity = weatherClient.getWeatherForCity(city.getName(), day);
            weatherCityList.add(weatherForCity);
        }
        return weatherCityList;
    }

    public List<Weather> validSurfingCondition(List<Weather> weatherCityList) {
        return weatherCityList
                .stream()
                .filter(this::validWeatherCondition)
                .toList();
    }

    private boolean validWeatherCondition(Weather weather) {
        return weather.getTemperature() >= MIN_TEMPERATURE
                && weather.getTemperature() <= MAX_TEMPERATURE
                && weather.getWindSpeed() >= MIN_WIND_SPEED
                && weather.getWindSpeed() <= MAX_WIND_SPEED;
    }

    private Weather calculateBestSurfingLocation(long day) {
        List<Weather> weatherCityList = validSurfingCondition(getWeatherForecastForAllCities(day));
        checkIfThereAreAnyGoodConditions(weatherCityList);

        return getCityWithBestCondition(weatherCityList);
    }

    public Weather getCityWithBestCondition(List<Weather> weatherCityList) {
        List<Weather> weatherList = new ArrayList<>();

        if (weatherCityList.size() == 1) {
            return weatherCityList.get(0);
        } else {
            weatherCityList.forEach(weather -> {
                weather.setFormulaValue(weather.getTemperature() + MULTIPLIER * weather.getWindSpeed());
                weatherList.add(weather);
            });
            return weatherList.stream()
                    .max(Comparator.comparing(Weather::getFormulaValue))
                    .get();
        }
    }

    private void checkIfThereAreAnyGoodConditions(List<Weather> weatherCityList) {
        if (weatherCityList.isEmpty()) {
            throw new WeatherAppException(WeatherError.BAD_WEATHER_CONDITIONS_FOR_ALL_CITIES);
        }
    }

    private WeatherDto mapToDto(Weather weather) {
        return WeatherDto.builder()
                .cityName(weather.getCityName())
                .temperature(weather.getTemperature())
                .windSpeed(weather.getWindSpeed())
                .lat(weather.getLat())
                .lon(weather.getLon())
                .build();
    }
}
