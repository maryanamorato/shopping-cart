package com.navalia.shopping.cart.domains.entity;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private Product(Integer id, String name, Double price, Integer amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public static Product from(Integer id, String name, Double price, Integer amount) {
        return new Product(id, name, price, amount);
    }

    public Product() {
        super();
    }
}
