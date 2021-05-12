package com.es.phoneshop.core.order;

import com.es.phoneshop.core.cart.CartServiceBahtinova;
import com.es.phoneshop.core.model.order.OrderBahtinova;
import com.es.phoneshop.core.model.order.OrderDaoBahtinova;
import com.es.phoneshop.core.model.order.OrderItemBahtinova;
import com.es.phoneshop.core.model.order.OrderStatusBahtinova;
import com.es.phoneshop.core.model.phone.PhoneBahtinova;
import com.es.phoneshop.core.model.phone.PhoneDaoBahtinova;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


@Service
@PropertySource("classpath:application.properties")
public class OrderServiceImplBahtinova implements OrderServiceBahtinova {
    private static Long orderId = 0L;

    @Value("${delivery.price}")
    private Long deliveryPrice;

    @Resource
    private CartServiceBahtinova cartService;

    @Resource
    private PhoneDaoBahtinova phoneDao;

    @Resource
    private OrderDaoBahtinova orderDao;

    @Override
    public OrderBahtinova createOrder() {
        OrderBahtinova order = new OrderBahtinova();
        List<OrderItemBahtinova> orderItems = new ArrayList<>();

        for(Map.Entry<PhoneBahtinova, Long> entry: cartService.getPhoneMap().entrySet()) {
            OrderItemBahtinova item = new OrderItemBahtinova();
            item.setOrder(order);
            item.setPhone(entry.getKey());
            item.setQuantity(entry.getValue());
            orderItems.add(item);
        }

        order.setOrderItems(orderItems);
        setPriceInfo(order);
        order.setId(++orderId);
        order.setStatus(OrderStatusBahtinova.NEW);
        order.setDate(new Date());

        return order;
    }

    @Override
    public void placeOrder(OrderBahtinova order) {
        orderDao.insertOrder(order);
        cartService.getCart().getCartItems().clear();

        for(OrderItemBahtinova item: order.getOrderItems()) {
            phoneDao.decreaseStock(item.getPhone().getId(), item.getQuantity());
        }
    }

    @Override
    public Long getDeliveryPrice() {
        return deliveryPrice;
    }

    public boolean checkOrderItemsStock() {
        boolean allStocksEnough = true;

        for(Map.Entry<Long, Long> cartEntry: new HashMap<>( cartService.getCart().getCartItems()).entrySet()) {
            if(cartEntry.getValue() > phoneDao.getStock(cartEntry.getKey()).get().getStock()) {
                allStocksEnough = false;
                cartService.remove(cartEntry.getKey());
            }
        }

        return allStocksEnough;
    }

    private void setPriceInfo(OrderBahtinova order) {
        BigDecimal subtotal = new BigDecimal(cartService.getOverallPrice());
        BigDecimal deliveryPrice = new BigDecimal(getDeliveryPrice());
        BigDecimal totalPrice = subtotal.add(deliveryPrice);

        order.setSubtotal(subtotal);
        order.setDeliveryPrice(deliveryPrice);
        order.setTotalPrice(totalPrice);
    }
}
