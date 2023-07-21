package com.navalia.shopping.cart.controller;

import com.navalia.shopping.cart.domains.dto.CheckoutResponse;
import com.navalia.shopping.cart.domains.dto.ProductRequest;
import com.navalia.shopping.cart.domains.entity.Cart;
import com.navalia.shopping.cart.exception.InvalidAmountException;
import com.navalia.shopping.cart.exception.InvalidProductException;
import com.navalia.shopping.cart.service.CartService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

@Controller("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Post(value = "/product", consumes = { MediaType.APPLICATION_JSON })
    @Status(HttpStatus.OK)
    Cart addProduct(@Body ProductRequest request) throws InvalidAmountException, InvalidProductException {
        return cartService.addProduct(request);
    }

    @Delete(value = "/product", consumes = { MediaType.APPLICATION_JSON })
    @Status(HttpStatus.OK)
    Cart removeProduct(@Body ProductRequest request) throws InvalidAmountException, InvalidProductException {
        return cartService.removeProduct(request);
    }

    @Delete(value = "/clear")
    @Status(HttpStatus.OK)
    Cart clearCart() {
        return cartService.clearCart();
    }

    @Get("/checkout")
    public CheckoutResponse checkout() {
        return cartService.checkout();
    }
}