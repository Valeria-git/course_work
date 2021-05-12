package com.es.phoneshop.core.model.phone;

public class StockBahtinova {
    private PhoneBahtinova phone;
    private Integer stock;
    private Integer reserved;

    public PhoneBahtinova getPhone() {
        return phone;
    }

    public void setPhone(PhoneBahtinova phone) {
        this.phone = phone;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }
}
