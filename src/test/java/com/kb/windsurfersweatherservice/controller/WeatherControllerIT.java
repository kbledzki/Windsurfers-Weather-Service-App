package com.kb.windsurfersweatherservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.windsurfersweatherservice.data.UtilsData;
import com.kb.windsurfersweatherservice.exceptions.WeatherExceptionDto;
import com.kb.windsurfersweatherservice.model.WeatherDto;
import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private WeatherClient weatherClient;

    @Test
    void should_return_best_location_for_surfing_for_given_city_forecast() throws Exception {
        //given
        LocalDate date = LocalDate.now().plusDays(5L);
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherJastarnia,
                        UtilsData.weatherBridgetown,
                        UtilsData.weatherFortaleza,
                        UtilsData.weatherPissouri);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/{date}", date))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName", Matchers.is("Bridgetown")))
                .andReturn();
        WeatherDto weatherDto = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), WeatherDto.class);
        //then
        verify(weatherClient).getWeatherForCity("Bridgetown", 5L);
        assertEquals(20.2, weatherDto.getTemperature());
    }

    @Test
    void should_return_bad_request_with_message_when_non_of_location_is_suitable_to_surfing() throws Exception {
        //given
        LocalDate date = LocalDate.now().plusDays(5L);
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherFortaleza,
                        UtilsData.weatherPissouri);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/{date}", date))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        WeatherExceptionDto weatherExceptionDto = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), WeatherExceptionDto.class);

        //then
        assertEquals("There is no good weather condition for surfing on that day. Try another day!",
                weatherExceptionDto.getMessage());
    }

    @Test
    void should_return_bad_request_with_message_when_given_data_is_wrong_format() throws Exception {
        //given
        String date = "2023_12_14";
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherFortaleza,
                        UtilsData.weatherPissouri,
                        UtilsData.weatherJastarnia);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/{date}", date))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        WeatherExceptionDto weatherExceptionDto = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), WeatherExceptionDto.class);

        //then
        assertEquals("Wrong date format! Please use ISO 8601 format YYYY-MM-DD.",
                weatherExceptionDto.getMessage());
    }

    @Test
    void should_return_bad_request_with_message_when_given_date_is_from_past() throws Exception {
        //given
        LocalDate date = LocalDate.now().minusDays(5L);
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherJastarnia,
                        UtilsData.weatherBridgetown,
                        UtilsData.weatherFortaleza,
                        UtilsData.weatherPissouri,
                        UtilsData.weatherGdansk);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/{date}", date))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        WeatherExceptionDto weatherExceptionDto = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), WeatherExceptionDto.class);

        //then
        assertEquals("Date should be current or future!",
                weatherExceptionDto.getMessage());
    }

    @Test
    void should_return_bad_request_with_message_when_given_date_is_too_distant() throws Exception {
        //given
        LocalDate date = LocalDate.now().plusDays(17L);
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherJastarnia,
                        UtilsData.weatherBridgetown,
                        UtilsData.weatherFortaleza,
                        UtilsData.weatherPissouri,
                        UtilsData.weatherGdansk);

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/{date}", date))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        WeatherExceptionDto weatherExceptionDto = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), WeatherExceptionDto.class);

        //then
        assertEquals("App show forecast only for 16 days!",
                weatherExceptionDto.getMessage());
    }

    @Test
    void should_return_expected_location_when_multiple_formula_is_the_same_for_more_than_one_city() throws Exception {
        //given
        LocalDate date = LocalDate.now().plusDays(5L);
        when(weatherClient.getWeatherForCity(anyString(), anyLong()))
                .thenReturn(UtilsData.weatherJastarnia,
                        UtilsData.weatherHel,
                        UtilsData.weatherGdansk);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/{date}", date))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cityName", Matchers.is("Hel")))
                .andReturn();
        WeatherDto weatherDto = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), WeatherDto.class);

        //then
        assertEquals(6.1, weatherDto.getWindSpeed());
    }
}