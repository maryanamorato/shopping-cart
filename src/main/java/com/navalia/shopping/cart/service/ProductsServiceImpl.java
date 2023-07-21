package com.navalia.shopping.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navalia.shopping.cart.domains.entity.Product;

import java.io.File;
import java.io.IOException;

public class ProductsServiceImpl {
    public static Product[] getCatalog() {
        ObjectMapper objectMapper = new ObjectMapper();
        Product[] products;

        try {
            products = objectMapper.readValue(new File("src/main/resources/fake_products_database.json"), Product[].class);
            return products;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}