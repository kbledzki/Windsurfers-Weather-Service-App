package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.model.CityName;
import com.kb.windsurfersweatherservice.model.Weather;
import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;
    private static final int MIN_WIND_SPEED = 5;
    private static final int MAX_WIND_SPEED = 18;
    private static final int MIN_TEMPERATURE = 5;
    private static final int MAX_TEMPERATURE = 35;

    public Weather getBestWeather() {
        String s = checkBestCity();
        return weatherClient.getWeatherForCity(s);
    }


    private List<Weather> getWeathers() {
        List<Weather> weatherCityList = new ArrayList<>();
        for (CityName cityName : CityName.values()) {
            Weather weatherForCity = weatherClient.getWeatherForCity(cityName.toString());
            weatherCityList.add(weatherForCity);
        }
        return forecastValid(weatherCityList);
    }

    private String checkBestCity() {
        List<Weather> weathers = getWeathers();
        return calculateBest(weathers);
    }


    private List<Weather> forecastValid(List<Weather> weatherCityList) {
        return weatherCityList
                .stream()
                .filter(weather -> weather.getTemperature() >= MIN_TEMPERATURE)
                .filter(weather -> weather.getTemperature() <= MAX_TEMPERATURE)
                .filter(weather -> weather.getWindSpeed() >= MIN_WIND_SPEED)
                .filter(weather -> weather.getWindSpeed() <= MAX_WIND_SPEED)
                .collect(Collectors.toList());
    }

    private String calculateBest(List<Weather> weatherList) {
        Map<String, Float> bestCodntionMap = new HashMap<>();
        weatherList.forEach(weather -> {
            float value = weather.getTemperature() + 3 * weather.getWindSpeed();
            bestCodntionMap.put(weather.getCityName(), value);
        });
        return Collections.max(bestCodntionMap.keySet());
    }


//    public List<Weather> getWeatherList2() {
//        List<Weather> weatherCityList = new ArrayList<>();
//        for (CityName cityName : CityName.values()) {
//            Weather weatherForCity = weatherClient.getWeatherForCity(cityName.toString());
//            weatherCityList.add(weatherForCity);
//        }
//        for (Weather wea : weatherCityList) {
//            System.out.println(wea);
//        }
//        List<Weather> newlist = forecastValid(weatherCityList);
//        for (Weather wea : newlist) {
//            System.out.println(wea);
//        }
//
//        Map<String, List<Weather>> groupedByName = newlist
//                .stream()
//                .collect(groupingBy(weather -> weather.getCityName()));
//        System.out.println(groupedByName);
//        System.out.println(groupedByName.size());
////        groupedByName.forEach((name, weather) -> ((3 * weather.getWindSpeed() + weather.getTemperature())))
//        System.out.println(groupedByName);
//        String calculateBest = calculateBest(newlist);
//        List<Weather> wewew = groupedByName.get(calculateBest(newlist));
//        for (Weather wea : wewew) {
//            System.out.println(wea);
//        }
//
//        List<Weather> weathers = newlist.stream().filter(weather -> weather.getCityName().equals(calculateBest)).toList();
//        for (Weather wea : weathers) {
//            System.out.println(wea);
//        }
//
//        return weatherCityList;
//    }
}
