# Shopping Cart API

## Project Overview

This is a Java REST API to handle a shopping cart and apply the "Get 3 for the price of 2" promotion. The API allows users to add and remove 
items from the shopping cart and calculate the total price with the promotion applied. It is also possible to clear the cart.

## Architecture and Design

The project follows a simple and straightforward architecture, consisting of the following components:

1. **Controller**: The `CartController` class is responsible for handling incoming HTTP requests and defining the API endpoints.

2. **Service**: The `CartServiceImpl` class contains the business logic for adding, removing items, clearing the cart and calculating the total price with the promotion.

3. **Domain -> Entity**: The `Cart` and `Product` classes represents the Cart and the Item, respectively.

4. **Domain -> DTO**: The `ProductRequest` and `CheckoutResponse` classes handle the endpoints input and output.

5. **Exception**: The `InvalidAmountException` and `InvalidProductException` classes represents custom exceptions for handling errors.

6. **Database**: The project uses a mocked Database to define the items available in the store. The source is
`resources/fake_products_database.json`

## How to Run the Project

1. Clone the repository or download the source code.

2. Open the project in your preferred Java IDE.

3. Build the project to resolve the dependencies.

4. Run the `Main` class to start the API.

## API Endpoints

### Add Item to Shopping Cart

- **HTTP Method**: POST
- **Endpoint**: `/api/shopping-cart/v1/cart/product'`
- **Request Body**:
  ```json
  {
    "productId": "1",
    "amount": 3
  }

- **Response**: Status 200 OK

### Remove Item from Shopping Cart

- **HTTP Method**: DELETE
- **Endpoint**: `/api/shopping-cart/v1/cart/product'`
- **Request Body**:
  ```json
  {
    "productId": "1",
    "amount": 3
  }

- **Response**: Status 200 OK

### Clear Shopping Cart

- **HTTP Method**: DELETE
- **Endpoint**: `/api/shopping-cart/v1/cart/clear`
- **Response**: Status 200 OK

### Checkout the Shopping Cart

- **HTTP Method**: GET
- **Endpoint**: `/api/shopping-cart/v1/cart/checkout'`
- **Response**: Status 200 OK
  ```json
  {
    "cart": [
      {
       "productId": "1",
        "amount": 3
      }
  ],
    "totalPrice": 25.98
  }