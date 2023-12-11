package com.kb.windsurfersweatherservice.webclient.weather.dto;

import lombok.Getter;

@Getter
public class WeatherbitForecastDto {

    private String city_name;//atnotacją załatwisz to że jest "_" i wteyd pole normalnie cityName
    private double lat;
    private double lon;
    private WeatherbitDataDto[] data;//można jako List<KlasaDto>
}
