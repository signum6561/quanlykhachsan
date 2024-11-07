package com.nhom3_221404.database;

import java.util.List;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceDatabaseBoundary;

public class SearchInvoiceDAOMySql implements SearchInvoiceDatabaseBoundary {

    private InvoiceRepository invoiceRepository;

    public SearchInvoiceDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> searchInvoice(String pattern) {
        try {
            return invoiceRepository.findByPattern(pattern);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
