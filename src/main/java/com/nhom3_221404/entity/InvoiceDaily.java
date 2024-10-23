package com.nhom3_221404.entity;

import com.nhom3_221404.database.repository.InvoiceRepository;

public class InvoiceDaily extends Invoice {

    private Integer rentalDays;

    public InvoiceDaily() {
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

    @Override
    public Invoice acceptInsert(InvoiceRepository invoiceRepository) {
        return invoiceRepository.insert(this);
    }

    @Override
    public Invoice acceptSave(InvoiceRepository invoiceRepository) {
        return invoiceRepository.save(this);
    }
}
