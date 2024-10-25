package com.nhom3_221404.entity;

import com.nhom3_221404.database.repository.InvoiceRepository;

public class InvoiceHourly extends Invoice {

    private Integer rentalHours;

    public InvoiceHourly() {
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

    @Override
    public Invoice acceptInsert(InvoiceRepository invoiceRepository) {
        return invoiceRepository.insert(this);
    }

    @Override
    public Invoice acceptSave(InvoiceRepository invoiceRepository) {
        return invoiceRepository.save(this);
    }

}
