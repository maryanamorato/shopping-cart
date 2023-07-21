package com.navalia.shopping.cart.service;

import com.navalia.shopping.cart.domains.dto.ProductRequest;
import com.navalia.shopping.cart.domains.entity.Cart;
import com.navalia.shopping.cart.domains.entity.Product;
import com.navalia.shopping.cart.exception.InvalidAmountException;
import com.navalia.shopping.cart.exception.InvalidProductException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {
    private ProductRequest productRequest;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cart = new Cart();
        productRequest = createRequest();

    }

    @Test
    void testAddProduct() throws InvalidProductException, InvalidAmountException {
        cart = cartService.addProduct(productRequest);

        assertEquals(1, cart.getProducts().size());
        assertEquals(2, cart.getProducts().stream().map(Product::getAmount).reduce(Integer::sum).orElse(0));
    }

    @Test
    void testAddProductTwice() throws InvalidProductException, InvalidAmountException {
        cartService.addProduct(productRequest);
        cart = cartService.addProduct(productRequest);

        assertEquals(1, cart.getProducts().size());
        assertEquals(4, cart.getProducts().stream().map(Product::getAmount).reduce(Integer::sum).orElse(0));
    }

    @Test
    void testAddProductWithInvalidProductId() {
        var productRequest = createInvalidProductRequest();

        assertThrows(InvalidProductException.class, () -> cartService.addProduct(productRequest));
    }

    @Test
    void testAddProductWithInvalidAmount() {
        var productRequest = createInvalidAmountRequest();

        assertThrows(InvalidAmountException.class, () -> cartService.addProduct(productRequest));
    }


    @Test
    void testRemoveProduct() throws InvalidProductException, InvalidAmountException {
        cartService.addProduct(productRequest);
        cart = cartService.removeProduct(new ProductRequest(1, 1));

        assertEquals(1, cart.getProducts().size());
        assertEquals(1, cart.getProducts().stream().map(Product::getAmount).reduce(Integer::sum).orElse(0));
    }

    @Test
    void testRemoveProductEmptyCart() {
        var productRequest = createInvalidAmountRequest();

        assertThrows(InvalidProductException.class, () -> cartService.removeProduct(productRequest));
    }

    @Test
    void testRemoveProductWithInvalidProductId() {
        var productRequest = createInvalidProductRequest();

        assertThrows(InvalidProductException.class, () -> cartService.removeProduct(productRequest));
    }

    @Test
    void testRemoveProductWithInvalidAmount() throws InvalidAmountException, InvalidProductException {
        cartService.addProduct(productRequest);

        var productRequest1 = createInvalidAmountRequest();

        assertThrows(InvalidAmountException.class, () -> cartService.removeProduct(productRequest1));
    }

    @Test
    void testClearCart() {
        cartService.clearCart();
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void testCheckout() throws InvalidAmountException, InvalidProductException {
        var productRequest = createRequest();
        var productRequest2 = createRequest2();
        var productRequest3 = createRequest3();

        cartService.addProduct(productRequest);
        cartService.addProduct(productRequest2);
        cart = cartService.addProduct(productRequest3);

        var response = cartService.checkout();

        assertNotNull(response);
        assertEquals(58.64, response.getTotalPrice());
    }

    private ProductRequest createRequest() {
      return new ProductRequest(1, 2);
    }

    private ProductRequest createRequest2() {
        return new ProductRequest(2, 1);
    }

    private ProductRequest createRequest3() {
        return new ProductRequest(3, 1);
    }

    private ProductRequest createInvalidProductRequest() {
        return new ProductRequest(4, 1);
    }

    private ProductRequest createInvalidAmountRequest() {
        return new ProductRequest(1, 0);
    }
}
