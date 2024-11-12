package com.nhom3_221404.database;

import java.util.List;

import com.google.inject.Inject;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.InvoiceType;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesDatabaseBoundary;

public class GetInvoiceTypesDAOMySql implements GetInvoiceTypesDatabaseBoundary {

    private final InvoiceRepository invoiceRepository;

    @Inject
    public GetInvoiceTypesDAOMySql(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<InvoiceType> getInvoiceTypes() {
        try {
            return invoiceRepository.findAllTypes();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
