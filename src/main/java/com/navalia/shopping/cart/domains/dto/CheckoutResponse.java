package com.navalia.shopping.cart.domains.entity;

import com.navalia.shopping.cart.domains.Item;
import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private List<Item> items;
}
