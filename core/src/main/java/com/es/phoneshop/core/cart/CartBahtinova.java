package com.es.phoneshop.core.cart;

import java.util.HashMap;
import java.util.Map;

public class CartBahtinova {
    private Map<Long, Long>  cartItems;

    public Map<Long, Long> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Long, Long> cartItems) {
        this.cartItems = cartItems;
    }

    public CartBahtinova() {
        cartItems = new HashMap<>();
    }
}
