package com.nhom3_221404.database;

import com.google.inject.Inject;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceDatabaseBoundary;

public class UpdateInvoiceDAOMySQL implements UpdateInvoiceDatabaseBoundary {
    private final InvoiceRepository invoiceRepository;

    @Inject
    public UpdateInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        try {
            return invoiceRepository.save(invoice);
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }

    @Override
    public Invoice getInvoiceById(String id) {
        try {
            return invoiceRepository.findById(id); 
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}