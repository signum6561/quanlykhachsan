package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceDatabaseBoundary;

public class CreateInvoiceDAOMySQL implements CreateInvoiceDatabaseBoundary {
    InvoiceRepository invoiceRepository;

    public CreateInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public String createInvoiceID(Invoice invoice) {
        return invoiceRepository.insert(invoice).getId();
    }

    @Override
    public Invoice findInvoiceID(String newInvoiceID) {
        return invoiceRepository.findById(newInvoiceID);
    }

}
