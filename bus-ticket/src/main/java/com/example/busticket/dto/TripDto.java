package com.example.busticket.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TripDto {

    private Long id;

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @NotNull
    @Future
    private LocalDateTime departureTime;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal price;

    public TripDto() {
    }

    public TripDto(Long id, String origin, String destination, LocalDateTime departureTime, BigDecimal price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public TripDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public TripDto setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public TripDto setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public TripDto setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TripDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
