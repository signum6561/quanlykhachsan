package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;

import java.time.Month;
import java.util.List;

public class AverageInvoiceDAOMySQL {
    private final InvoiceRepository invoiceRepository;

    public AverageInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public double calculateAverageInvoice(Month month) {
        List<Invoice> invoices = invoiceRepository.findAll();
        
        List<Invoice> filteredInvoices = invoices.stream()
            .filter(invoice -> invoice.getBilledDate().getMonth() == month)
            .toList();

        if (filteredInvoices.isEmpty()) {
            return 0.0; 
        }

        double totalAmount = 0.0;
        for (Invoice invoice : filteredInvoices) {
            totalAmount += invoice.getTotal(); 
        }

        return totalAmount / filteredInvoices.size();
    }
}