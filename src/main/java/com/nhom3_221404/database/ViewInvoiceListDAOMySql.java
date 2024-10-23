package com.nhom3_221404.database;

import java.util.List;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListDatabaseBoundary;

public class ViewInvoiceListDAOMySql implements ViewInvoiceListDatabaseBoundary {
    InvoiceRepository invoiceRepository;

    public ViewInvoiceListDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getInvoiceList() {
        return invoiceRepository.findAll();
    }
}
