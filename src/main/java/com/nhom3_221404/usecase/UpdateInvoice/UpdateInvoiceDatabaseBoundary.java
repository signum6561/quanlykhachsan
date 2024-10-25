package com.nhom3_221404.usecase.UpdateInvoice;

import com.nhom3_221404.entity.Invoice;

public interface UpdateInvoiceDatabaseBoundary {
    Invoice findInvoiceById(String id);
    Invoice updateInvoice(Invoice invoice);
}
