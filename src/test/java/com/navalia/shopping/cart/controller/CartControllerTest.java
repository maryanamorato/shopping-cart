package com.navalia.shopping.cart.controller;

import com.navalia.shopping.cart.domains.dto.CheckoutResponse;
import com.navalia.shopping.cart.domains.dto.ProductRequest;
import com.navalia.shopping.cart.domains.entity.Cart;
import com.navalia.shopping.cart.exception.InvalidAmountException;
import com.navalia.shopping.cart.exception.InvalidProductException;
import com.navalia.shopping.cart.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProduct() throws InvalidAmountException, InvalidProductException {
        ProductRequest request = new ProductRequest(1, 2);
        Cart expectedCart = new Cart();
        when(cartService.addProduct(request)).thenReturn(expectedCart);

        Cart result = cartController.addProduct(request);
        assertEquals(expectedCart, result);

        verify(cartService, times(1)).addProduct(request);
    }

    @Test
    void testRemoveProduct() throws InvalidAmountException, InvalidProductException {
        ProductRequest request = new ProductRequest(2,1);
        Cart expectedCart = new Cart();
        when(cartService.removeProduct(request)).thenReturn(expectedCart);

        Cart result = cartController.removeProduct(request);
        assertEquals(expectedCart, result);

        verify(cartService, times(1)).removeProduct(request);
    }

    @Test
    void testClearCart() {
        Cart expectedCart = new Cart();
        when(cartService.clearCart()).thenReturn(expectedCart);

        Cart result = cartController.clearCart();
        assertEquals(expectedCart, result);

        verify(cartService, times(1)).clearCart();
    }

    @Test
    void testCheckout() {
        CheckoutResponse expectedResponse = new CheckoutResponse(new Cart(), 25.98);
        when(cartService.checkout()).thenReturn(expectedResponse);

        CheckoutResponse result = cartController.checkout();
        assertEquals(expectedResponse, result);

        verify(cartService, times(1)).checkout();
    }
}