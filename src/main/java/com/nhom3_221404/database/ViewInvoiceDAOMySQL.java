package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceDatabaseBoundary;

public class ViewInvoiceDAOMySQL implements ViewInvoiceDatabaseBoundary {
    InvoiceRepository invoiceRepository;

    public ViewInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice viewInvoice(String viewInvoiceInputDTO) {
        return invoiceRepository.findById(viewInvoiceInputDTO);
    }
}
