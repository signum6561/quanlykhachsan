package com.nhom3_221404.entity;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceType;

public class InvoiceHourly extends Invoice {

    private Integer rentalHours;

    public InvoiceHourly() {
        invoiceType = InvoiceType.Hourly;
    }

    public InvoiceHourly(Integer rentalHours) {
        this.rentalHours = rentalHours;
    }

    public InvoiceHourly(String id, String roomId, InvoiceType invoiceType, Double price, String customerName,
            LocalDate billedDate, Integer rentalHours) {
        super(id, roomId, invoiceType, price, customerName, billedDate);
        this.rentalHours = rentalHours;
    }

    @Override
    public Double getTotal() {
        if (price == null) {
            throw new IllegalStateException("Price must be set before calculating total.");
        }
        if (rentalHours > 24 && rentalHours < 30) {
            return 24 * price; // Tính giá cho 24 giờ
        }
        return rentalHours * price; // Tính giá theo số giờ thuê
    }

    // #region getters and setters
    public Integer getRentalHours() {
        return rentalHours;
    }

    public void setRentalHours(Integer rentalHours) {
        this.rentalHours = rentalHours;
    }
    // #endregion

    @Override
    public String toString() {
        return "InvoiceHourly [id=" + id
                + ", rentalHours=" + rentalHours
                + ", roomId=" + roomId
                + ", price=" + price
                + ", customerName=" + customerName
                + ", billedDate=" + billedDate + "]";
    }
}
