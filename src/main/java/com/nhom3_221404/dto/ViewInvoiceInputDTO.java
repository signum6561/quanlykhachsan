package com.nhom3_221404.dto;

import java.time.LocalDateTime;
import com.nhom3_221404.common.InvoiceType;

public class ViewInvoiceInputDTO {
    private String id;
    private String roomId;
    private Double price;
    private String customerName;
    private LocalDateTime billedDate;
    private InvoiceType invoiceType;

    private Integer rentalDays;

    private Integer rentalHours;

    public ViewInvoiceInputDTO(String id, String roomId, Double price, String customerName, LocalDateTime billedDate,
            InvoiceType invoiceType) {
        this.id = id;
        this.roomId = roomId;
        this.price = price;
        this.customerName = customerName;
        this.billedDate = billedDate;
        this.invoiceType = invoiceType;
    }

    public ViewInvoiceInputDTO(String id, String roomId, Double price, String customerName, LocalDateTime billedDate,
            InvoiceType invoiceType, Integer rentalDays) {
        this(id, roomId, price, customerName, billedDate, invoiceType);
        this.rentalDays = rentalDays;
    }

    public ViewInvoiceInputDTO(String id, String roomId, String customerName, Double price, LocalDateTime billedDate,
            InvoiceType invoiceType, Integer rentalHours) {
        this(id, roomId, price, customerName, billedDate, invoiceType);
        this.rentalHours = rentalHours;
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

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Integer getRentalHours() {
        return rentalHours;
    }

    public void setRentalHours(Integer rentalHours) {
        this.rentalHours = rentalHours;
    }

}
