package com.kb.windsurfersweatherservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class WeatherDto {

    private String cityName;
    private double temperature;
    private double windSpeed;
    private double lat;
    private double lon;
}

