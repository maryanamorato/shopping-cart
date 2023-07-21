package com.navalia.shopping.cart.domains.dto;

import com.navalia.shopping.cart.domains.entity.Cart;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public record CheckoutResponse(Cart cart, Double totalPrice) {
    public static CheckoutResponse from(final Cart cart, final Double totalPrice) {
        return new CheckoutResponse(cart, totalPrice);
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
