package com.kb.windsurfersweatherservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherAppException extends RuntimeException {
    private WeatherError weatherError;
}
