package com.nhom3_221404.entity;

import java.time.LocalDateTime;

import com.nhom3_221404.common.InvoiceType;

public class InvoiceDaily extends Invoice {

    private Integer rentalDays;

    public InvoiceDaily() {
    }

    public InvoiceDaily(String id, String roomId, InvoiceType invoiceType, Double price, String customerName,
            LocalDateTime billedDate, Integer rentalDays) {
        super(id, roomId, invoiceType, price, customerName, billedDate);
        this.rentalDays = rentalDays;
    }

    @Override
    public Double getTotal() {
        if (rentalDays - 7 < 0) {
            return rentalDays * price;
        }
        return (1.4 + 0.8 * rentalDays) * price;
    }

    // #region getters and setters
    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }
    // #endregion

    @Override
    public String toString() {
        return "InvocieDaily [id=" + id
                + ", rentalDays=" + rentalDays
                + ", roomId=" + roomId
                + ", price=" + price
                + ", customerName=" + customerName
                + ", billedDate=" + billedDate + "]";
    }
}
