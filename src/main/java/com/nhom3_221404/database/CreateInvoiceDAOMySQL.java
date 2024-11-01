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
    public Invoice addInvoice(Invoice invoice) {
        try {
            invoiceRepository.insert(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
