package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.exceptions.WrongDataFormatException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

@Service
public class DataService {

    LocalDate whenCreated = LocalDate.of(2023, Month.DECEMBER, 8);

    public void checkDays(String dateToCheck) {
        LocalDate dataNow = LocalDate.now();
        LocalDate dataToCheckWeather = parseDate(dateToCheck);

        System.out.println(dataNow);
        System.out.println(whenCreated);
        Duration difference = Duration.between(dataNow.atStartOfDay(), whenCreated.atStartOfDay());
        long days = Duration.between(dataNow.atStartOfDay(), whenCreated.atStartOfDay()).toDays();
        System.out.println(difference);
        System.out.println(days);
    }

    private LocalDate parseDate(String dateToCheck) {
        LocalDate datanew;
        try {
            datanew = LocalDate.parse(dateToCheck);
        } catch (Exception e) {
            throw new WrongDataFormatException("zły format");
        }
        return datanew;
    }
}
