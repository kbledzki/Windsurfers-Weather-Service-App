package com.kb.windsurfersweatherservice.webclient.weather.dto;

import lombok.Getter;

@Getter
public class WeatherbitForecastDto {
    private String city_name;
    private float lat;
    private float lon;
    private WeatherbitDataDto[] data;
}
