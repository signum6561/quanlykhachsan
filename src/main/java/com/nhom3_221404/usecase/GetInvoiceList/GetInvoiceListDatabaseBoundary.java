package com.nhom3_221404.usecase.GetInvoiceList;

import java.util.List;

import com.nhom3_221404.entity.Invoice;

public interface GetInvoiceListDatabaseBoundary {
    List<Invoice> getInvoiceList();
}
