package com.nhom3_221404.usecase.PaginateInvoiceList;

import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public interface PaginateInvoiceListDatabaseBoundary {
    Page<Invoice> getPaginatedInvoices(Pageable pageable);
}
