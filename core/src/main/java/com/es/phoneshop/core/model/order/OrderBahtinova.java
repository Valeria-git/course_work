package com.es.phoneshop.core.model.order;

import org.hibernate.validator.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderBahtinova
{
    private Long id;
    private List<OrderItemBahtinova> orderItems;
    private BigDecimal subtotal;
    private BigDecimal deliveryPrice;
    private BigDecimal totalPrice;
    private Date date;

    @NotBlank(message="This field is required")
    private String firstName;

    @NotBlank(message="This field is required")
    private String lastName;

    @NotBlank(message="This field is required")
    private String deliveryAddress;

    @NotBlank(message="This field is required")
    private String contactPhoneNo;

    private String additionalInfo;

    private OrderStatusBahtinova status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemBahtinova> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemBahtinova> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    public OrderStatusBahtinova getStatus() {
        return status;
    }

    public void setStatus(OrderStatusBahtinova status) {
        this.status = status;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
