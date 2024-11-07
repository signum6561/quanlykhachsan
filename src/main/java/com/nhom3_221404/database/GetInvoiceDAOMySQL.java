package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceDatabaseBoundary;

public class GetInvoiceDAOMySQL implements GetInvoiceDatabaseBoundary {
    InvoiceRepository invoiceRepository;

    public GetInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice getInvoice(String getInvoiceInputDTO) {
        return invoiceRepository.findById(getInvoiceInputDTO);
    }
}
