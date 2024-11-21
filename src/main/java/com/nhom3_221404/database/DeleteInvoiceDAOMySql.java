package com.nhom3_221404.database;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceDatabaseBoundary;

public class DeleteInvoiceDAOMySql implements DeleteInvoiceDatabaseBoundary {
    private final InvoiceRepository invoiceRepository;

    @Inject
    public DeleteInvoiceDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    
    @Transactional
    public void deleteInvoice(String id) {
        Invoice invoice = invoiceRepository.findById(id);
        invoiceRepository.delete(invoice);
    }
}