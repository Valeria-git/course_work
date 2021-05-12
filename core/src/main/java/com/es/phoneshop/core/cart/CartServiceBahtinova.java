package com.es.phoneshop.core.cart;

import com.es.phoneshop.core.model.phone.PhoneBahtinova;

import java.util.Map;

public interface CartServiceBahtinova {

    CartBahtinova getCart();

    void addPhone(Long phoneId, Long quantity);

    /**
     * @param items
     * key: {@link PhoneBahtinova#id}
     * value: quantity
     */
    void update(Map<Long, Long> items);

    void remove(Long phoneId);

    long getItemsNum();

    long getItemNumById(Long phoneId);

    int getOverallPrice();

    boolean isNotEnoughStock(Long phoneId, Long quantity);

    Map<PhoneBahtinova,Long> getPhoneMap();

    void setColors(PhoneBahtinova phone);
}
