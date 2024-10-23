package com.nhom3_221404.entity;

import java.time.LocalDateTime;

import com.nhom3_221404.common.InvocieDataModel;

public abstract class Invoice implements InvocieDataModel {

    protected String id;

    protected String roomId;

    protected Double price;

    protected String customerName;

    protected LocalDateTime billedDate;

    public Invoice() {
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
    // #endregion
}
