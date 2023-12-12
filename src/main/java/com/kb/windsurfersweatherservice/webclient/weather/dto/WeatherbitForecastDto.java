package com.kb.windsurfersweatherservice.webclient.weather.dto;

import lombok.Getter;

@Getter
public class WeatherbitForecastDto {

    private String city_name;
    private double lat;
    private double lon;
    private WeatherbitDataDto[] data;
}
