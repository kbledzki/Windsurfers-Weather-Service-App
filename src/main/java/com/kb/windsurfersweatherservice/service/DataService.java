package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.exceptions.WeatherAppException;
import com.kb.windsurfersweatherservice.exceptions.WeatherError;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

@Service
public class DataService {

    public long checkDays(String dateToCheck) {
        LocalDate dataNow = LocalDate.now();
        LocalDate dataToCheckWeather = parseDate(dateToCheck);
        return Duration
                .between(dataNow.atStartOfDay(), dataToCheckWeather.atStartOfDay())
                .toDays();
    }

    private LocalDate parseDate(String dateToCheck) {
        try {
            return LocalDate.parse(dateToCheck);
        } catch (Exception e) {
            throw new WeatherAppException(WeatherError.WRONG_DATA_FORMAT);
        }
    }
}
