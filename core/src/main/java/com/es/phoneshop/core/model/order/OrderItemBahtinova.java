package com.es.phoneshop.core.model.order;

import com.es.phoneshop.core.model.phone.PhoneBahtinova;

public class OrderItemBahtinova {
    private PhoneBahtinova phone;
    private OrderBahtinova order;
    private Long quantity;

    public PhoneBahtinova getPhone() {
        return phone;
    }

    public void setPhone(final PhoneBahtinova phone) {
        this.phone = phone;
    }

    public OrderBahtinova getOrder() {
        return order;
    }

    public void setOrder(final OrderBahtinova order) {
        this.order = order;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }
}
