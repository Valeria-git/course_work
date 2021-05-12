package com.es.phoneshop.core.model.order;

import com.es.phoneshop.core.model.phone.PhoneBahtinova;
import com.es.phoneshop.core.model.phone.PhoneDaoBahtinova;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcOrderDaoBahtinova implements OrderDaoBahtinova {
    private final static String SQL_INSERT_ORDER_QUERY = "insert into orders values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_INSERT_PHONE2ORDER_QUERY = "insert into phone2order values(?, ?, ?)";
    private final static String SQL_FIND_ORDERS_QUERY = "select * from orders";
    private final static String SQL_FIND_ITEMS_QUERY = "select phoneId, quantity from phone2order where orderId = ?";
    private final static String SQL_FIND_ORDER = "select * from orders where id = ?";
    private final static String SQL_CHANGE_STATUS_QUERY = "update orders set statusId = ? where id = ?";
    private final static String ID_LABEL = "id";
    private final static String SUBTOTAL_LABEL = "subtotal";
    private final static String DELIVERY_PRICE_LABEL = "deliveryPrice";
    private final static String TOTAL_PRICE_LABEL = "totalPrice";
    private final static String FIRSTNAME_LABEL = "firstName";
    private final static String LASTNAME_LABEL = "lastName";
    private final static String DELIVERY_ADDRESS_LABEL = "deliveryAddress";
    private final static String CONTACT_PHONE_NUMBER_LABEL = "contactPhoneNo";
    private final static String STATUSID_LABEL = "statusId";
    private final static String QUANTITY_LABEL = "quantity";
    private final static String PHONEID_LABEL = "phoneId";
    private final static String DATE_LABEL = "orderDate";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private PhoneDaoBahtinova phoneDao;

    private void insertIntoOrders(final OrderBahtinova order) {
        jdbcTemplate.update(SQL_INSERT_ORDER_QUERY, new Object[]{order.getId(), order.getSubtotal(), order.getDeliveryPrice(),
                order.getTotalPrice(), order.getFirstName(), order.getLastName(), order.getDeliveryAddress(),
                order.getContactPhoneNo(), (order.getStatus().ordinal() + 1), order.getDate()});
    }

    private void insertIntoPhone2Order(final OrderBahtinova order) {
        for(OrderItemBahtinova item: order.getOrderItems()) {
            jdbcTemplate.update(SQL_INSERT_PHONE2ORDER_QUERY, new Object[]{order.getId(), item.getPhone().getId(), item.getQuantity()});
        }
    }

    @Transactional
    public void insertOrder(final OrderBahtinova order) {
        insertIntoOrders(order);
        insertIntoPhone2Order(order);
    }

    private void setOrderListPhoneData(List<OrderBahtinova> orderList) {
        for(OrderBahtinova order: orderList) {
            setOrderPhoneData(order);
        }
    }

    private void setOrderPhoneData(OrderBahtinova order) {
        for(OrderItemBahtinova orderItem: order.getOrderItems()) {
            Optional<PhoneBahtinova> phone = phoneDao.get(orderItem.getPhone().getId());
            if(phone.isPresent()) {
                PhoneBahtinova gotPhone = phone.get();
                gotPhone.setColors(new HashSet<>(phoneDao.getPhoneColors(gotPhone.getId())));
                orderItem.setPhone(gotPhone);
            }
        }
    }

    private void setOrderListItems(List<OrderBahtinova> orderList) {
        for(OrderBahtinova order: orderList) {
            setOrderItems(order);
        }
    }

    private void setOrderItems(OrderBahtinova order) {
        List<OrderItemBahtinova> orderItemList = jdbcTemplate.query(SQL_FIND_ITEMS_QUERY, new Object[]{order.getId()},
                (ResultSet rs, int rowNumber) -> {
                    OrderItemBahtinova orderItem = new OrderItemBahtinova();
                    orderItem.setQuantity(rs.getLong(QUANTITY_LABEL));
                    orderItem.setPhone(new PhoneBahtinova());
                    orderItem.getPhone().setId(rs.getLong(PHONEID_LABEL));
                    orderItem.setOrder(order);
                    return orderItem;
                });

        order.setOrderItems(orderItemList);
    }

    private List<OrderBahtinova> selectOrders() {
        List<OrderBahtinova> orderList = jdbcTemplate.query(SQL_FIND_ORDERS_QUERY, (ResultSet rs, int rowNumber) -> {
            OrderBahtinova order = new OrderBahtinova();
            order.setId(rs.getLong(ID_LABEL));
            order.setSubtotal(rs.getBigDecimal(SUBTOTAL_LABEL));
            order.setDeliveryPrice(rs.getBigDecimal(DELIVERY_PRICE_LABEL));
            order.setTotalPrice(rs.getBigDecimal(TOTAL_PRICE_LABEL));
            order.setFirstName(rs.getString(FIRSTNAME_LABEL));
            order.setLastName(rs.getString(LASTNAME_LABEL));
            order.setDeliveryAddress(rs.getString(DELIVERY_ADDRESS_LABEL));
            order.setContactPhoneNo(rs.getString(CONTACT_PHONE_NUMBER_LABEL));
            order.setStatus(OrderStatusBahtinova.values()[rs.getInt(STATUSID_LABEL)-1]);
            order.setDate(rs.getDate(DATE_LABEL));
            return order;
        });

        return orderList;
    }

    private Optional<OrderBahtinova> selectOrderById(Long id) {
        try {
            OrderBahtinova optionalOrder = jdbcTemplate.queryForObject(SQL_FIND_ORDER, new Object[]{id}, (ResultSet rs, int rowNumber) -> {
                OrderBahtinova order = new OrderBahtinova();
                order.setId(rs.getLong(ID_LABEL));
                order.setSubtotal(rs.getBigDecimal(SUBTOTAL_LABEL));
                order.setDeliveryPrice(rs.getBigDecimal(DELIVERY_PRICE_LABEL));
                order.setTotalPrice(rs.getBigDecimal(TOTAL_PRICE_LABEL));
                order.setFirstName(rs.getString(FIRSTNAME_LABEL));
                order.setLastName(rs.getString(LASTNAME_LABEL));
                order.setDeliveryAddress(rs.getString(DELIVERY_ADDRESS_LABEL));
                order.setContactPhoneNo(rs.getString(CONTACT_PHONE_NUMBER_LABEL));
                order.setStatus(OrderStatusBahtinova.values()[rs.getInt(STATUSID_LABEL) - 1]);
                order.setDate(rs.getDate(DATE_LABEL));
                return order;
            });
            return Optional.of(optionalOrder);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public List<OrderBahtinova> findAll() {
        List<OrderBahtinova> orderList = selectOrders();
        setOrderListItems(orderList);
        setOrderListPhoneData(orderList);
        return orderList;
    }

    @Transactional
    public Optional<OrderBahtinova> findById(Long orderId) {
        Optional<OrderBahtinova> order = selectOrderById(orderId);
        if(order.isPresent()) {
            setOrderItems(order.get());
            setOrderPhoneData(order.get());
            return order;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void changeStatus(Long orderId, Integer status) {
        jdbcTemplate.update(SQL_CHANGE_STATUS_QUERY, new Object[]{status, orderId});
    }
}
