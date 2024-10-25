package com.nhom3_221404.dto;

import java.time.LocalDateTime;

public class CreateInvoiceOutDTO {
    private String id;
    private String roomId;
    private Double price;
    private String customerName;
    private LocalDateTime billedDate;
    private Double total;

    public CreateInvoiceOutDTO(String id, String roomId, Double price, String customerName, LocalDateTime billedDate,
            Double total) {
        this.id = id;
        this.roomId = roomId;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
        this.total = total;
    }

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

}
