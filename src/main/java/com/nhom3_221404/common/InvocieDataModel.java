package com.nhom3_221404.common;

import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;

public interface InvocieDataModel {
    Invoice acceptInsert(InvoiceRepository invoiceRepository);

    Invoice acceptSave(InvoiceRepository invoiceRepository);
}
