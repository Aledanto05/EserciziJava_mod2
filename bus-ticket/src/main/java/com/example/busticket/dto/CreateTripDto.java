package com.example.busticket.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateTripDto {

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @NotNull
    private LocalDateTime departureTime;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal price;

    public CreateTripDto() {
    }

    public CreateTripDto(String origin, String destination, LocalDateTime departureTime, BigDecimal price) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public CreateTripDto setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public CreateTripDto setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public CreateTripDto setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateTripDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
