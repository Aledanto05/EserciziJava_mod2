package com.dslab.ecommercelab.entity;

import javax.persistence.*;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Product product;

    private Integer quantity;

    @Transient
    public Double getTotal() {
        return product.getPrice()*quantity;
    }

    public Integer getId() {
        return id;
    }

    public OrderProduct setId(Integer id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderProduct setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
