package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.exceptions.WeatherAppException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataServiceTest {

    private DataService dataService;

    @BeforeEach
    void beforeEach() {
        dataService = new DataService();
    }

    @Test
    void should_return_days_when_given_date() {
        //given
        try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
            LocalDate currentDate = LocalDate.of(2023, 12, 01);
            mockedStatic.when(LocalDate::now).thenReturn(currentDate);

            //when
            long days = dataService.calculateDayToCheckWeather(UtilsData.date);

            //then
            assertEquals(days, 11);
        }
    }

    @Test
    void should_throw_exception_when_date_is_from_past() {
        //given
        String pastDate = UtilsData.pastDate;

        //when&then

        assertThrows(WeatherAppException.class,
                () -> dataService.calculateDayToCheckWeather(pastDate));
    }

    @Test
    void should_throw_exception_when_date_is_too_distant() {
        //given
        String pastDate = UtilsData.futureDate;

        //when&then
        assertThrows(WeatherAppException.class,
                () -> dataService.calculateDayToCheckWeather(pastDate));
    }

    @Test
    void should_throw_exception_when_date_is_in_wrong_format() {
        //given
        String pastDate = UtilsData.futureDate;

        //when&then
        assertThrows(WeatherAppException.class,
                () -> dataService.calculateDayToCheckWeather(pastDate));
    }
}