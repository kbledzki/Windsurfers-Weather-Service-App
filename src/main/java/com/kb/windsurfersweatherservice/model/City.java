package com.kb.windsurfersweatherservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum City {
    JASTARNIA("Jastarnia", 54.69606, 18.67873),
    BRIDGETOWN("Bridgetown", 13.10732, -59.62021),
    FORTALEZA("Fortaleza", -3.71722, -38.54306),
    PISSOURI("Pissouri", 34.66942, 32.70132),
    LE_MORNE("Le Morne", 14.7, -61.0);

    private final String name;
    private final double lat;
    private final double lon;
}
