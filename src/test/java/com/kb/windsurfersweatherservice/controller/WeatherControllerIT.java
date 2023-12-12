//package com.kb.windsurfersweatherservice.controller;
//
//import com.github.tomakehurst.wiremock.junit5.WireMockTest;
//import com.kb.windsurfersweatherservice.model.Weather;
//import com.kb.windsurfersweatherservice.service.WeatherService;
//import com.kb.windsurfersweatherservice.webclient.weather.client.WeatherClient;
//import org.apache.commons.io.IOUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@WireMockTest(httpPort = 8080)
//class WeatherControllerIT {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private WeatherService weatherService;
//
//    @Autowired
//    private WeatherClient weatherClient;
//
//    @DynamicPropertySource
//    static void configure(DynamicPropertyRegistry registry) {
//        registry.add("WEATHER.URL", () -> "http://localhost:8080");
//    }
//
//    @Test
//    void should_return_correct_payment() throws IOException {
//        // given
//        String responseBody = IOUtils.resourceToString("/file/correct-response.json", StandardCharsets.UTF_8);
//
//        stubFor(get(urlEqualTo("/api/v1/weather/2023-12-18"))
//                .willReturn(
//                        aResponse()
//                                .withStatus(200)
//                                .withHeader("Content-Type", "application/json")
//                                .withBody(responseBody)
//                )
//        );
//        Weather response = weatherClient.getWeatherForCity("Sopot",13);
////        BankChargeResponse response = bankPaymentService.sendPaymentToBank(new BankChargeRequest(100, "4790627202424467", "141"));
//
//
//    }
//
//    @Test
//    void getWeather() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/2023-12-18"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//    }
//
//    @Test
//    void getWeatherBad() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/2023-11-18"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andReturn();
//    }
//
//    @Test
//    void getWeatherBadwire() throws Exception {
//
//
//    }
//
////    @Test
////    void test() throws Exception {
////        StringWriter writer = new StringWriter();
////
////        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
////        server.expect(requestTo("https://api.weatherbit.io/v2.0/forecast/daily?city=Sopot&key=66964f620c5d4dd9b43244e2021fdf6a"))
////                .andRespond(withSuccess("BLA", MediaType.A WeatherbitForecastDto.class));
////
////        objectMapper.writeValue(writer, weatherClient.getWeatherForCity ("Sopot",10));
////
//////        assertEquals(result, "BLA");
////
////    }
//}