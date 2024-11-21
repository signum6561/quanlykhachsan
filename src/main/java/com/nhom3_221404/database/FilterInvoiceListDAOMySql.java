package com.nhom3_221404.database;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListDatabaseBoundary;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public class FilterInvoiceListDAOMySql implements FilterInvoiceListDatabaseBoundary {

    private final InvoiceRepository invoiceRepository;

    @Inject
    public FilterInvoiceListDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Page<Invoice> getFilteredInvoices(String pattern, Pageable pageable) {
        return invoiceRepository.findByPattern(pattern, pageable);
    }

}
