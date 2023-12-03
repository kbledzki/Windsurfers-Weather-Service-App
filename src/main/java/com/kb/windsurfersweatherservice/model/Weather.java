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
    private float temperature;
    private float windSpeed;
    private float lat;
    private float lon;
}
