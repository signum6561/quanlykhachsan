package com.nhom3_221404.database;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceDatabaseBoundary;

public class CreateInvoiceDAOMySql implements CreateInvoiceDatabaseBoundary {
    private final InvoiceRepository invoiceRepository;

    @Inject
    public CreateInvoiceDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public void createInvoice(Invoice invoice) {
        invoiceRepository.insert(invoice);
    } 
}
