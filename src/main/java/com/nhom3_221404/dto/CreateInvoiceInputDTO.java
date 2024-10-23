package com.nhom3_221404.dto;

import java.time.LocalDateTime;

import com.nhom3_221404.common.InvoiceType;

public class CreateInvoiceInputDTO {
    private String roomId;
    private Double price;
    private String customerName;
    private LocalDateTime billedDate;
    private InvoiceType invoiceType;

    public CreateInvoiceInputDTO(InvoiceType invoiceType, String roomId, Double price, String customerName,
            LocalDateTime billedDate) {
        this.invoiceType = invoiceType;
        this.roomId = roomId;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
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
}
