package com.navalia.shoppingcart.entity;

import com.navalia.shoppingcart.constant.ItemEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item  {
    private int amount;
    private ItemEnum itemData;
}
