package com.kb.windsurfersweatherservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class WeatherAppExceptionHandler {

    @ExceptionHandler(WeatherAppException.class)
    public ResponseEntity<Object> handleException(WeatherAppException e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (WeatherError.WRONG_DATE_FORMAT.equals(e.getWeatherError())
                || WeatherError.BAD_WEATHER_CONDITIONS_FOR_ALL_CITIES.equals(e.getWeatherError())
                || WeatherError.PAST_DATE.equals(e.getWeatherError())
                || WeatherError.TOO_DISTANT_DATE.equals(e.getWeatherError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status", httpStatus.value());
        map.put("message", e.getWeatherError().getMessage());
        map.put("path", request.getDescription(false));
        return new ResponseEntity<>(map, httpStatus);
    }
}
