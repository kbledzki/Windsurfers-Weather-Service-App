package com.kb.windsurfersweatherservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherError {

    WRONG_DATE_FORMAT("Wrong date format! Please use ISO 8601 format YYYY-MM-DD."),
    PAST_DATE("Date should be current or future!"),
    TOO_DISTANT_DATE("App show forecast only for 16 days!"),
    BAD_WEATHER_CONDITIONS_FOR_ALL_CITIES("There is no good weather condition for surfing on that day. Try another day!");


    private String message;
}
