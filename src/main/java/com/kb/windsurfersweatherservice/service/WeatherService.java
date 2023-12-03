package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.model.CityName;
import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    private static final int MIN_WIND_SPEED = 5;
    private static final int MAX_WIND_SPEED = 18;
    private static final int MIN_TEMPERATURE = 5;
    private static final int MAX_TEMPERATURE = 35;

    public Weather getWeather() {
        return weatherClient.getWeatherForCity("Sopot");
    }

    public List<Weather> getWeatherList() {
        List<Weather> weatherCityList = new ArrayList<>();
        for (CityName cityName : CityName.values()) {
            Weather weatherForCity = weatherClient.getWeatherForCity(cityName.toString());
            weatherCityList.add(weatherForCity);
        }
        for (Weather wea : weatherCityList) {
            System.out.println(wea);
        }
        List<Weather> newlist = forecastValid(weatherCityList);
        for (Weather wea : newlist) {
            System.out.println(wea);
        }

        return weatherCityList;
    }

    public List<Weather> getWeatherListv2() {
        List<Weather> weatherCityList = new ArrayList<>();
        for (CityName cityName : CityName.values()) {
            Weather weatherForCity = weatherClient.getWeatherForCity(cityName.toString());
            weatherCityList.add(weatherForCity);
        }
        for (Weather wea : weatherCityList) {
            System.out.println(wea);
        }
        List<Weather> newlist = forecastValidv2(weatherCityList);
        for (Weather wea : newlist) {
            System.out.println(wea);
        }

        return weatherCityList;
    }


    public List<Weather> forecastValid(List<Weather> weatherCityList) {
        return weatherCityList
                .stream()
                .filter(weather -> weather.getTemperature() >= MIN_TEMPERATURE)
                .filter(weather -> weather.getTemperature() <= MAX_TEMPERATURE)
                .filter(weather -> weather.getWindSpeed() >= MIN_WIND_SPEED)
                .filter(weather -> weather.getWindSpeed() <= MAX_WIND_SPEED)
                .collect(Collectors.toList());
    }

    public List<Weather> forecastValidv2(List<Weather> weatherCityList) {
        return weatherCityList
                .stream()
                .filter(weather -> validateWeatherCondition(weather))
                .collect(Collectors.toList());
    }

    private boolean validateWeatherCondition(Weather weather) {
        return weather.getTemperature() >= MIN_TEMPERATURE
                && weather.getTemperature() <= MAX_TEMPERATURE
                && weather.getWindSpeed() >= MIN_WIND_SPEED
                && weather.getWindSpeed() <= MAX_WIND_SPEED;
    }
}
