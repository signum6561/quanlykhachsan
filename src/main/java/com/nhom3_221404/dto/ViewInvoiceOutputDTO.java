package com.nhom3_221404.dto;

import java.time.LocalDate;

public class ViewInvoiceOutputDTO {
    private String id;
    private String invoiceType;
    private String roomId;
    private Double price;
    private String customerName;
    private LocalDate billedDate;
    private Double total;
    private Integer rentalDays;
    private Integer rentalHours;

    public ViewInvoiceOutputDTO() {
    }

    public ViewInvoiceOutputDTO(String id, String invoiceType, String roomId, Double price, String customerName, LocalDate billedDate, Double total, Integer rentalDays, Integer rentalHours) {
        this.id = id;
        this.invoiceType = invoiceType;
        this.roomId = roomId;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
        this.total = total;
        this.rentalDays = rentalDays;
        this.rentalHours = rentalHours;
    }

    public ViewInvoiceOutputDTO(String id, String invoiceType, String roomId, Double price, String customerName,
            LocalDate billedDate, Double total) {
        this.id = id;
        this.invoiceType = invoiceType;
        this.roomId = roomId;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
        this.total = total;
    }

    // #region getters and setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getBilledDate() {
        return this.billedDate;
    }

    public void setBilledDate(LocalDate billedDate) {
        this.billedDate = billedDate;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getRentalDays() {
        return this.rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Integer getRentalHours() {
        return this.rentalHours;
    }

    public void setRentalHours(Integer rentalHours) {
        this.rentalHours = rentalHours;
    }

    // #endregion
}
