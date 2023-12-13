package com.kb.windsurfersweatherservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class WeatherAppExceptionHandler {

    private final List<WeatherError> errorList = List.of(WeatherError.values());

    @ExceptionHandler(WeatherAppException.class)
    public ResponseEntity<WeatherExceptionDto> handleException(WeatherAppException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (errorList.stream().anyMatch(error -> error.equals(e.getWeatherError()))) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus)
                .body(new WeatherExceptionDto(
                        e.getWeatherError().getMessage(),
                        LocalDateTime.now(),
                        httpStatus.value()));
    }
}