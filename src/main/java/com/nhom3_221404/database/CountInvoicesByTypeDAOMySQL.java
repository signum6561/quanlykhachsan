package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.usecase.CountInvoicesByType.CountInvoicesByTypeDatabaseBoundary;

public class CountInvoicesByTypeDAOMySQL implements CountInvoicesByTypeDatabaseBoundary {
    private final InvoiceRepository invoiceRepository;

    public CountInvoicesByTypeDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public int countInvoicesByType(String invoiceType) {
        return invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getInvoiceType().getCode().equals(invoiceType))
                .mapToInt(invoice -> 1)
                .sum();
    }
}