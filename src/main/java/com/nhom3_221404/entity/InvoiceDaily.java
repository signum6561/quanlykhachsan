package com.nhom3_221404.entity;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceType;

public class InvoiceDaily extends Invoice {

    private Integer rentalDays;
  
    public InvoiceDaily() {
        invoiceType = InvoiceType.Daily;
    }

    public InvoiceDaily(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public InvoiceDaily(String id, String roomId, Double price, String customerName,
            LocalDate billedDate, Integer rentalDays) {
        super(id, roomId, InvoiceType.Daily, price, customerName, billedDate);
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
