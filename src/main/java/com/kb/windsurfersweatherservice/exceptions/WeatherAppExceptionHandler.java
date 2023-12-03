package com.kb.windsurfersweatherservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WeatherAppExceptionHandler {

    @ExceptionHandler(WeatherAppException.class)
    public ResponseEntity<ErrorInfo> handleException(WeatherAppException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (WeatherError.WRONG_DATE_FORMAT.equals(e.getWeatherError())
                || WeatherError.BAD_WEATHER_CONDITIONS_FOR_ALL_CITIES.equals(e.getWeatherError())
                || WeatherError.PAST_DATE.equals(e.getWeatherError())
                || WeatherError.TOO_DISTANT_DATE.equals(e.getWeatherError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getWeatherError().getMessage()));
    }
}
