package com.dh.usersservice.model;

import java.util.Objects;

public class Bill {
    private final String idBill;
    private final String customerBill;
    private final String productBill;
    private final Double totalPrice;

    Bill(String idBill, String customerBill, String productBill, Double totalPrice) {
        this.idBill = idBill;
        this.customerBill = customerBill;
        this.productBill = productBill;
        this.totalPrice = totalPrice;
    }

    public String idBill() {
        return idBill;
    }

    public String customerBill() {
        return customerBill;
    }

    public String productBill() {
        return productBill;
    }

    public Double totalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Bill) obj;
        return Objects.equals(this.idBill, that.idBill) &&
                Objects.equals(this.customerBill, that.customerBill) &&
                Objects.equals(this.productBill, that.productBill) &&
                Objects.equals(this.totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBill, customerBill, productBill, totalPrice);
    }

    @Override
    public String toString() {
        return "Bill[" +
                "idBill=" + idBill + ", " +
                "customerBill=" + customerBill + ", " +
                "productBill=" + productBill + ", " +
                "totalPrice=" + totalPrice + ']';
    }

}
