package com.nhom3_221404.usecase.ViewInvoiceList;

import java.util.List;

import com.nhom3_221404.entity.Invoice;

public interface ViewInvoiceListDatabaseBoundary {
    List<Invoice> getInvoiceList();
}
