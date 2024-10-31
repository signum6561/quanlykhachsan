package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceDatabaseBoundary;

public class ViewInvoiceDAOMySQL implements SearchInvoiceDatabaseBoundary {
    InvoiceRepository invoiceRepository;

    public ViewInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice searchInvoice(String searchInvoiceInputDTO) {
        return invoiceRepository.findById(searchInvoiceInputDTO);
    }
}
