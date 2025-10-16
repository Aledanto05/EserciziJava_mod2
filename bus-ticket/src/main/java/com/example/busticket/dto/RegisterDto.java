package com.example.busticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class RegisterDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private BigDecimal initialCredit;

    public RegisterDto() {
    }

    public RegisterDto(String email, String password, BigDecimal initialCredit) {
        this.email = email;
        this.password = password;
        this.initialCredit = initialCredit;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }

    public RegisterDto setInitialCredit(BigDecimal initialCredit) {
        this.initialCredit = initialCredit;
        return this;
    }
}
