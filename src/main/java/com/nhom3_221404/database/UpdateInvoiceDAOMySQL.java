package com.nhom3_221404.database;

import org.mybatis.guice.transactional.Transactional;

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

    @Transactional
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}