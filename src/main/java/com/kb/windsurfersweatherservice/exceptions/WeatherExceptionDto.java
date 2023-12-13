package com.kb.windsurfersweatherservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class WeatherExceptionDto {

    private String message;
    private LocalDateTime timestamp;
    private int status;
}
