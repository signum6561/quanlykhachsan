package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.entity.Invoice;

public interface CreateInvoiceDatabaseBoundary {

    Invoice addInvoice(Invoice invoice);
}
