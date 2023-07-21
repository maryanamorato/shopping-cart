package com.navalia.shopping.cart.service;

public class CartService {
    @Override
    public OrderResponse addToCart(OrderRequest order) throws InvalidOrderException {
        if (validOrder(order)) {
            var itemData = ItemEnum.valueOfId(order.getItemId());
            var optItem = cart.getItems().stream().filter(i -> i.getItemData().getId() == order.getItemId()).findFirst();

            updateCart(itemData, order, OperationEnum.ADD, optItem);

            return OrderResponse.builder()
                    .message("Order placed. Item(s) added to cart.")
                    .build();
        } else {
            log.error(String.join(" ", LOGGING_PREFIX, "Order with invalid data detected:", order.toString()));
            throw new InvalidOrderException("The order received contains invalid data. This item may not exist with this id or amount.");
        }
    }
}
