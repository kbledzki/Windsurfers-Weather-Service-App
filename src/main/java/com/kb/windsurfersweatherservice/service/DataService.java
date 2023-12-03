package com.kb.windsurfersweatherservice.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

@Service
public class DataService {

    LocalDate whenCreated = LocalDate.of(2023, Month.DECEMBER, 8);

    public void checkDays() {
        LocalDate dataNow = LocalDate.now();

        System.out.println(dataNow);
        System.out.println(whenCreated);
        Duration difference = Duration.between(dataNow.atStartOfDay(), whenCreated.atStartOfDay());
        long days = Duration.between(dataNow.atStartOfDay(), whenCreated.atStartOfDay()).toDays();
        System.out.println(difference);
        System.out.println(days);
    }
}
