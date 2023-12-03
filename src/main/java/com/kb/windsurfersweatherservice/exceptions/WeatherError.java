package com.kb.windsurfersweatherservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherError {
    WRONG_DATA_FORMAT("Wrong data format! Please use ISO 8601 format YYYY-MM-DD");

    private String message;
}
