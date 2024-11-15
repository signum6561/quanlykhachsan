package com.nhom3_221404.database;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;

import java.util.List;

public class AverageInvoiceDAOMySQL {
    private final InvoiceRepository invoiceRepository;

    public AverageInvoiceDAOMySQL(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public double calculateAverageInvoice() {
        List<Invoice> invoices = invoiceRepository.findAll();
        if (invoices.isEmpty()) {
            return 0.0;
        }

        double totalAmount = 0.0;
        for (Invoice invoice : invoices) {
            totalAmount += invoice.getTotal(); // Giả sử mỗi Invoice có phương thức getTotal()
        }

        return totalAmount / invoices.size();
    }
}