package com.nhom3_221404.entity;

import java.time.LocalDateTime;

import com.nhom3_221404.common.InvoiceType;

public abstract class Invoice {

    protected String id;

    protected String roomId;

    protected InvoiceType invoiceType;

    protected Double price;

    protected String customerName;

    protected LocalDateTime billedDate;

    public Invoice() {
    }

    public Invoice(String id, String roomId, InvoiceType invoiceType, Double price, String customerName,
            LocalDateTime billedDate) {
        this.id = id;
        this.roomId = roomId;
        this.invoiceType = invoiceType;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
    }

    public abstract Double getTotal();

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

    public LocalDateTime getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(LocalDateTime billedDate) {
        this.billedDate = billedDate;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    // #endregion
}
