package com.dslab.ecommercelab.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class FinalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderProduct> products;

    private String state;

    @Transient
    public Double getTotal(){
        double total = 0.0;

        for (OrderProduct product : products)
            total +=product.getTotal();
        return total;
    }

    public Integer getId() {
        return id;
    }

    public FinalOrder setId(Integer id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public FinalOrder setUser(User user) {
        this.user = user;
        return this;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public FinalOrder setProducts(List<OrderProduct> products) {
        this.products = products;
        return this;
    }

    public String getState() {
        return state;
    }

    public FinalOrder setState(String state) {
        this.state = state;
        return this;
    }
}
