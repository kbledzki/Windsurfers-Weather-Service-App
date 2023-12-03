package com.kb.windsurfersweatherservice.exceptions;

public class WrongDataFormatException extends RuntimeException {
    public WrongDataFormatException(String message) {
        super(message);
    }
}
