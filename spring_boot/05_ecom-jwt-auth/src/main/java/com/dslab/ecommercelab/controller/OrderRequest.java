package com.dslab.ecommercelab.controller;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class OrderRequest {
    @NotNull
    private String userEmail;
    @NotNull
    private Map<Integer, Integer> products;

    public String getUserEmail() {
        return userEmail;
    }

    public OrderRequest setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public OrderRequest setProducts(Map<Integer, Integer> products) {
        this.products = products;
        return this;
    }
}
