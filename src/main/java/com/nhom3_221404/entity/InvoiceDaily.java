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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((rentalDays == null) ? 0 : rentalDays.hashCode());
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
        InvoiceDaily other = (InvoiceDaily) obj;
        if (rentalDays == null) {
            if (other.rentalDays != null)
                return false;
        } else if (!rentalDays.equals(other.rentalDays))
            return false;
        return true;
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
        return "InvoiceDaily [id=" + id
                + ", rentalDays=" + rentalDays
                + ", roomId=" + roomId
                + ", price=" + price
                + ", customerName=" + customerName
                + ", billedDate=" + billedDate + "]";
    }

}
