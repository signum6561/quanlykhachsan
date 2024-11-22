package com.nhom3_221404.entity;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceConstantType;
import com.nhom3_221404.database.repository.InvoiceArchiver;

public abstract class Invoice {

    protected String id;

    protected String roomId;

    protected Double price;

    protected InvoiceType invoiceType;

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

    public abstract InvoiceConstantType getType();

    public abstract void acceptInsert(InvoiceArchiver archiver);

    public abstract void acceptUpdate(InvoiceArchiver archiver);

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((invoiceType == null) ? 0 : invoiceType.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((billedDate == null) ? 0 : billedDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Invoice other = (Invoice) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (roomId == null) {
            if (other.roomId != null)
                return false;
        } else if (!roomId.equals(other.roomId))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (invoiceType == null) {
            if (other.invoiceType != null)
                return false;
        } else if (!invoiceType.equals(other.invoiceType))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (billedDate == null) {
            if (other.billedDate != null)
                return false;
        } else if (!billedDate.equals(other.billedDate))
            return false;
        return true;
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

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }
    // #endregion
}
