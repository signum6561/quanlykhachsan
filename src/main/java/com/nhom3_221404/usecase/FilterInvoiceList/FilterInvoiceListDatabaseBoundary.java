package com.nhom3_221404.usecase.FilterInvoiceList;

import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public interface FilterInvoiceListDatabaseBoundary {
    Page<Invoice> getFilteredInvoices(String pattern, Pageable pageable);    
}
