package com.example.busticket.dto;

import java.math.BigDecimal;

public class UserDto {

    private Long id;
    private String email;
    private BigDecimal credit;

    public UserDto() {
    }

    public UserDto(Long id, String email, BigDecimal credit) {
        this.id = id;
        this.email = email;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public UserDto setCredit(BigDecimal credit) {
        this.credit = credit;
        return this;
    }
}
