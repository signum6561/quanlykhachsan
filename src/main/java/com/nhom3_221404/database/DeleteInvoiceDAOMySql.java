package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;

public class DeleteInvoiceDAOMySql implements DeleteInvoiceDatabaseBoundary {
    private InvoiceRepository invoiceRepository;
    public DeleteInvoiceDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    @Override
    public boolean deleteInvoice(String invoiceId) {
        try {
            invoiceRepository.delete(invoiceId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}