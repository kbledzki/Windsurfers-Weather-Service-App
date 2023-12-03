package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.exceptions.WeatherAppException;
import com.kb.windsurfersweatherservice.exceptions.WeatherError;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class DataService {

    private static final int MAX_FORECAST_DAY = 15;

    public long calculateDayToCheckWeather(String dateToCheck) {
        LocalDate dataNow = LocalDate.now();
        LocalDate dataToCheckWeather = parseDate(dateToCheck);
        long days = Duration
                .between(dataNow.atStartOfDay(), dataToCheckWeather.atStartOfDay())
                .toDays();
        validDate(dataNow, dataToCheckWeather, days);
        return days;
    }

    private void validDate(LocalDate dataNow, LocalDate dataToCheckWeather, long days) {
        if (dataToCheckWeather.isBefore(dataNow)) {
            throw new WeatherAppException(WeatherError.PAST_DATE);
        } else if (days > MAX_FORECAST_DAY) {
            throw new WeatherAppException(WeatherError.TOO_DISTANT_DATE);
        }
    }

    private LocalDate parseDate(String dateToCheck) {
        try {
            return LocalDate.parse(dateToCheck);
        } catch (Exception e) {
            throw new WeatherAppException(WeatherError.WRONG_DATE_FORMAT);
        }
    }
}
