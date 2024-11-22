package com.nhom3_221404.entity;

import java.time.LocalDate;

import com.nhom3_221404.common.InvoiceConstantType;
import com.nhom3_221404.database.repository.InvoiceArchiver;

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

    @Override
    public InvoiceConstantType getType() {
        return InvoiceConstantType.HOURLY;
    }

    @Override
    public void acceptInsert(InvoiceArchiver archiver) {
        archiver.insert(this);
    }

    @Override
    public void acceptUpdate(InvoiceArchiver archiver) {
        archiver.update(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((rentalHours == null) ? 0 : rentalHours.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvoiceHourly other = (InvoiceHourly) obj;
        if (rentalHours == null) {
            if (other.rentalHours != null)
                return false;
        } else if (!rentalHours.equals(other.rentalHours))
            return false;
        return true;
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
