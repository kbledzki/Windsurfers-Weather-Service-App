package com.kb.windsurfersweatherservice.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Weather {

    private String cityName;
    private float temperature;
    private float windSpeed;
}
