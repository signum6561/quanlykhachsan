package com.nhom3_221404.entity;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceType;

public abstract class Invoice {

    protected String id;

    protected String roomId;

    protected Double price;

    protected String customerName;

    protected LocalDate billedDate;

    public Invoice() {
    }

    public Invoice(String id, String roomId, Double price, String customerName, LocalDate billedDate) {
        this.id = id;
        this.roomId = roomId;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
    }

    public abstract Double getTotal();

    public abstract InvoiceType getInvoiceType();

    // #region getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(LocalDate billedDate) {
        this.billedDate = billedDate;
    }

    // #endregion
}
