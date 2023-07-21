package com.navalia.shoppingcart.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/cart")
public class CartController {
    @Get
    @Produces
    public String index() {
        return "Hello World";
    }
}