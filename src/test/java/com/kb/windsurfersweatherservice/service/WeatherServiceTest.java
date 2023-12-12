package com.kb.windsurfersweatherservice.service;

import com.kb.windsurfersweatherservice.exceptions.WeatherAppException;
import com.kb.windsurfersweatherservice.model.WeatherDto;
import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherClient weatherClient;
    @Mock
    private DataService dataService;
    @InjectMocks
    private WeatherService weatherService;

    @Test
    void should_return_the_best_location_to_surf() {
        //given
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherJastarnia,
                        UtilsData.weatherBridgetown);
        when(dataService.calculateDayToCheckWeather(anyString())).thenReturn(UtilsData.days);

        //when
        WeatherDto bestLocationToSurf = weatherService.getBestLocationToSurf(UtilsData.date);

        //then
        assertEquals(bestLocationToSurf.getCityName(), UtilsData.weatherBridgetown.getCityName());
    }

    @Test
    void should_throw_exception_when_non_of_location_is_suitable_for_surfing() {
        //given
        when(weatherClient.getWeatherForCity(anyString(), anyLong())).thenReturn(UtilsData.weatherFortaleza);
        when(dataService.calculateDayToCheckWeather(anyString())).thenReturn(UtilsData.days);

        //when&then
        assertThrows(WeatherAppException.class,
                () -> weatherService.getBestLocationToSurf(UtilsData.date));
    }

    @Test
    void should_return_expected_city_when_formula_value_is_the_same() {
        //given
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherHel,UtilsData.weatherGdansk);
        when(dataService.calculateDayToCheckWeather(anyString())).thenReturn(UtilsData.days);

        //when
        WeatherDto bestLocationToSurf = weatherService.getBestLocationToSurf(UtilsData.date);

        //then
        assertEquals(bestLocationToSurf.getCityName(), UtilsData.weatherHel.getCityName());
    }
}