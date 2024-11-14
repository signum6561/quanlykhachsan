package com.nhom3_221404.database;

import com.google.inject.Inject;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceDatabaseBoundary;

public class CreateInvoiceDAOMySQL implements CreateInvoiceDatabaseBoundary {
    private final InvoiceRepository invoiceRepository;

    @Inject
    public CreateInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public boolean createInvoice(Invoice invoice) {
        try {
            invoiceRepository.insert(invoice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    } 
}
