package com.devsu.examen.customerservice.dto;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ApiErrorResponse {
    private final Integer status;
    private final String message;
    private final Instant timestamp = Instant.now();

    public ApiErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
