package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.model.Weather;

public class UtilsData {
    public static final Weather weatherJastarnia = Weather.builder()
            .cityName("Jastarnia")
            .temperature(5.0)
            .windSpeed(10.2)
            .build();
    public static final Weather weatherBridgetown = Weather.builder()
            .cityName("Bridgetown")
            .temperature(20.2)
            .windSpeed(6.1)
            .build();
    public static final Weather weatherFortaleza = Weather.builder()
            .cityName("Fortaleza")
            .temperature(4.1)
            .windSpeed(4.1)
            .build();
    public static final Weather weatherPissouri = Weather.builder()
            .cityName("Pissouri")
            .temperature(5.1)
            .windSpeed(5.1)
            .build();
    public static final Weather weatherHel = Weather.builder()
            .cityName("Hel")
            .temperature(20.2)
            .windSpeed(6.1)
            .build();
    public static final Weather weatherGdansk = Weather.builder()
            .cityName("Gdansk")
            .temperature(20.2)
            .windSpeed(6.1)
            .build();
    public static final String date = "2023-12-12";
    public static final String pastDate = "2022-12-12";
    public static final String futureDate = "2032-12-12";
    public static final Long days = 4L;
}
