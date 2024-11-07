package com.nhom3_221404.dto;

import java.time.LocalDate;

public class UpdateInvoiceInputDTO {
    private String id; // New field for identifying the invoice to update
    private String roomId;
    private Double price;
    private String customerName;
    private LocalDate billedDate;
    private String invoiceType;
    private int rentalDays;
    private int rentalHours;

    public UpdateInvoiceInputDTO() {
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
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

    public LocalDate getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(LocalDate billedDate) {
        this.billedDate = billedDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getRentalHours() {
        return rentalHours;
    }

    public void setRentalHours(int rentalHours) {
        this.rentalHours = rentalHours;
    }
}
