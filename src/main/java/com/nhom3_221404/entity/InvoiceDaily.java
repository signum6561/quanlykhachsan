package com.nhom3_221404.entity;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceConstantType;
import com.nhom3_221404.database.repository.InvoiceArchiver;

public class InvoiceDaily extends Invoice {

    private Integer rentalDays;
  
    public InvoiceDaily() {
    }

    public InvoiceDaily(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public InvoiceDaily(String id, String roomId, Double price, String customerName,
            LocalDate billedDate, Integer rentalDays) {
        super(id, roomId, price, customerName, billedDate);
        this.rentalDays = rentalDays;
    }

    @Override
    public Double getTotal() {
        if (rentalDays - 7 < 0) {
            return rentalDays * price;
        }
        return (1.4 + 0.8 * rentalDays) * price;
    }

    @Override
    public InvoiceConstantType getType() {
        return InvoiceConstantType.DAILY;
    }

    @Override
    public void acceptInsert(InvoiceArchiver archiver) {
        archiver.insert(this);
    }

    @Override
    public void acceptUpdate(InvoiceArchiver archiver) {
        archiver.update(this);
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
