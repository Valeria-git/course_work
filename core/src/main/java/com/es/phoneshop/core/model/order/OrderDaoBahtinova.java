package com.es.phoneshop.core.model.order;

import java.util.List;
import java.util.Optional;

public interface OrderDaoBahtinova {
    void insertOrder(final OrderBahtinova order);
    List<OrderBahtinova> findAll();
    Optional<OrderBahtinova> findById(Long orderId);
    void changeStatus(Long orderId, Integer status);
}
