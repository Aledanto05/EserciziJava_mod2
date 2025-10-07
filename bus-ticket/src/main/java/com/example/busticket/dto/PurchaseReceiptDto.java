package com.example.busticket.dto;

import java.math.BigDecimal;

public class PurchaseReceiptDto {

    private Long userId;
    private Long tripId;
    private BigDecimal charge;
    private BigDecimal remainingBalance;

    public PurchaseReceiptDto() {
    }

    public PurchaseReceiptDto(Long userId, Long tripId, BigDecimal charge, BigDecimal remainingBalance) {
        this.userId = userId;
        this.tripId = tripId;
        this.charge = charge;
        this.remainingBalance = remainingBalance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
}
