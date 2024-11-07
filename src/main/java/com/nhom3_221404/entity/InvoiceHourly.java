package com.nhom3_221404.entity;

import java.time.LocalDate;

public class InvoiceHourly extends Invoice {

    private Integer rentalHours;

    public InvoiceHourly() {
    }

    public InvoiceHourly(Integer rentalHours) {
        this.rentalHours = rentalHours;
    }

    public InvoiceHourly(String id, String roomId, Double price, String customerName,
            LocalDate billedDate, Integer rentalHours) {
        super(id, roomId, price, customerName, billedDate);
        this.rentalHours = rentalHours;
    }

    @Override
    public Double getTotal() {
        if (rentalHours > 24 && rentalHours < 30) {
            return 24 * price;
        }
        return rentalHours * price;
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
