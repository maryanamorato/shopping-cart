package com.navalia.shopping.cart.service;

import com.navalia.shopping.cart.domains.dto.CheckoutResponse;
import com.navalia.shopping.cart.domains.dto.ProductRequest;
import com.navalia.shopping.cart.domains.entity.Cart;
import com.navalia.shopping.cart.exception.InvalidAmountException;
import com.navalia.shopping.cart.exception.InvalidProductException;

public interface CartService {

    Cart addProduct(ProductRequest productRequest) throws InvalidProductException, InvalidAmountException;
    Cart removeProduct(ProductRequest productRequest) throws InvalidProductException, InvalidAmountException;
    Cart clearCart();
    CheckoutResponse checkout();

}