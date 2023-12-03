package com.kb.windsurfersweatherservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    private DataService dataService;

    @BeforeEach
    void beforeEach() {
        dataService = new DataService();
    }

    @Test
    void should_test() {
        dataService.checkDays();
    }
}