package com.navalia.shopping.cart.service;

import com.navalia.shopping.cart.constants.OperationEnum;
import com.navalia.shopping.cart.domains.dto.CheckoutResponse;
import com.navalia.shopping.cart.domains.dto.ProductRequest;
import com.navalia.shopping.cart.domains.entity.Cart;
import com.navalia.shopping.cart.domains.entity.Product;
import com.navalia.shopping.cart.exception.InvalidAmountException;
import com.navalia.shopping.cart.exception.InvalidProductException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.navalia.shopping.cart.constants.MessageConstants.ERROR_INVALID_AMOUNT;
import static com.navalia.shopping.cart.constants.MessageConstants.ERROR_INVALID_PRODUCT;
import static com.navalia.shopping.cart.constants.OperationEnum.ADD;
import static com.navalia.shopping.cart.constants.OperationEnum.REMOVE;
import static com.navalia.shopping.cart.service.ProductsServiceImpl.getCatalog;

public class CartServiceImpl implements CartService {
    private final Cart cart = new Cart();

    @Override
    public Cart addProduct(ProductRequest productRequest) throws InvalidProductException, InvalidAmountException {
        var product = productInCart(productRequest.productId());
        return changeCart(product, productRequest, ADD);
    }

    @Override
    public Cart removeProduct(ProductRequest productRequest) throws InvalidProductException, InvalidAmountException {
        var product = productInCart(productRequest.productId());
        product.orElseThrow(() -> new InvalidProductException(ERROR_INVALID_PRODUCT));

        return changeCart(product, productRequest, REMOVE);
    }

    @Override
    public Cart clearCart() {
        cart.setProducts(new ArrayList<>());
        return cart;
    }

    @Override
    public CheckoutResponse checkout() {
        var sortedPrices = cart.getProducts().stream()
                .flatMap(product -> IntStream.range(0, product.getAmount())
                        .mapToObj(i -> product.getPrice()))
                .sorted().toList();

        var discountedProducts = sortedPrices.size() / 3;

        Double totalPrice = sortedPrices.stream()
                .skip(discountedProducts)
                .mapToDouble(price -> price)
                .sum();

        return CheckoutResponse.from(cart, totalPrice);
    }

    public Cart changeCart(Optional<Product> productInCart, ProductRequest productRequest, OperationEnum operation) throws InvalidProductException, InvalidAmountException {
        var productId = productRequest.productId();
        var product = validateProduct(productId);

        if (productRequest.amount() < 1) throw new InvalidAmountException(ERROR_INVALID_AMOUNT);

        productInCart.ifPresentOrElse(
                p -> p.setAmount(doOperation(operation, p.getAmount(), productRequest.amount())),
                () -> cart.getProducts().add(
                        Product.from(productId,
                                product.getName(),
                                product.getPrice(),
                                productRequest.amount()))
        );

        return cart;
    }

    public Optional<Product> searchId(List<Product> products, Integer idToSearch) {
        return products.stream().filter(p -> p.getId().equals(idToSearch)).findFirst();
    }

    public Product validateProduct(Integer productId) throws InvalidProductException {
        var catalog = new ArrayList<>(Arrays.asList(getCatalog()));
        return searchId(catalog, productId).orElseThrow(() -> new InvalidProductException(ERROR_INVALID_PRODUCT));
    }

    public Optional<Product> productInCart(Integer productId) {
        return searchId(cart.getProducts(), productId);
    }

    public Integer doOperation (OperationEnum operation, Integer amount, Integer newAmount) {
        switch (operation) {
            case ADD -> { return amount + newAmount; }
            case REMOVE -> { return Math.max(amount - newAmount, 0); }
        }

        return 0;
    }
}
