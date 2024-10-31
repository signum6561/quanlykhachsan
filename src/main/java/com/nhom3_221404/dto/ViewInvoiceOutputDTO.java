package com.nhom3_221404.dto;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceType;

public class ViewInvoiceOutputDTO {
    protected String id;
    protected InvoiceType invoiceType;
    protected String roomId;
    protected Double price;
    protected String customerName;
    protected LocalDate billedDate;
    protected Double total;

    public ViewInvoiceOutputDTO() {
    }

    public ViewInvoiceOutputDTO(String id, InvoiceType invoiceType, String roomId, Double price, String customerName,
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public InvoiceType getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }
    // #endregion
}
