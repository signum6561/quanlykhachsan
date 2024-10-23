package com.nhom3_221404.database;

import java.util.List;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;

public class ViewInvoiceListDAOMySql {
    InvoiceRepository invoiceRepository;

    public ViewInvoiceListDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceRepository.findAll();
    }
}
