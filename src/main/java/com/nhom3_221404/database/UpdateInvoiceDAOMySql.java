package com.nhom3_221404.database;

import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceDatabaseBoundary;
import com.nhom3_221404.database.repository.InvoiceRepository;

public class UpdateInvoiceDAOMySql implements UpdateInvoiceDatabaseBoundary {

    private final InvoiceRepository invoiceRepository;

    public UpdateInvoiceDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice findInvoiceById(String id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        try {

            return invoiceRepository.save(invoice); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
