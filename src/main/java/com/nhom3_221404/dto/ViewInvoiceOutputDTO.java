package com.nhom3_221404.dto;

import java.time.LocalDateTime;

public class ViewInvoiceOutputDTO {
    protected String id;
    protected String roomId;
    protected Double price;
    protected String customerName;
    protected LocalDateTime billedDate;
    protected Double total;

    public ViewInvoiceOutputDTO() {
    }

    public ViewInvoiceOutputDTO(String id, String roomId, double price, String customerName, LocalDateTime billedDate,
            Double total) {
        this.id = id;
        this.roomId = roomId;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
        this.total = total;
    }

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    // #endregion
}
