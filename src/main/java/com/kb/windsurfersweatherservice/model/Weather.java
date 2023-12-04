package com.kb.windsurfersweatherservice.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Weather {

    private String cityName;
    private double temperature;
    private double windSpeed;
    private double lat;
    private double lon;
}
