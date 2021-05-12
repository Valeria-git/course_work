package com.es.phoneshop.core.order;

import com.es.phoneshop.core.model.order.OrderBahtinova;

public interface OrderServiceBahtinova {
    OrderBahtinova createOrder();
    void placeOrder(OrderBahtinova order);
    Long getDeliveryPrice();
    boolean checkOrderItemsStock();
}
