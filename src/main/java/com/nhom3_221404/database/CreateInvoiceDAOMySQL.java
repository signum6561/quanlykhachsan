package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;

public class CreateInvoiceDAOMySQL {
    InvoiceRepository invoiceRepository;

    public CreateInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public InvoiceRepository getInvoiceRepository() {
        return invoiceRepository;
    }

    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

}
