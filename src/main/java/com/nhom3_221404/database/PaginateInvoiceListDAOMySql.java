package com.nhom3_221404.database;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListDatabaseBoundary;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public class PaginateInvoiceListDAOMySql implements PaginateInvoiceListDatabaseBoundary {

    private final InvoiceRepository invoiceRepository;

    @Inject
    public PaginateInvoiceListDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Page<Invoice> getPaginatedInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

}
