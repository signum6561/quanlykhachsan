package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;

public class DeleteInvoiceDAOMemory implements DeleteInvoiceDatabaseBoundary {
    private final InvoiceRepository invoiceRepository;
    public DeleteInvoiceDAOMemory(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    @Override
    public boolean deleteInvoice(String invoiceId) {
        try {
            invoiceRepository.delete(invoiceId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}