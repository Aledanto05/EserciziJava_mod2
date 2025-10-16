package com.example.busticket.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TopUpDto {

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    public TopUpDto() {
    }

    public TopUpDto(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TopUpDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
