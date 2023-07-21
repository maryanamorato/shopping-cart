package com.navalia.shopping.cart.domains.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable.Deserializable
public record ProductRequest(Integer productId, Integer amount) {
}
