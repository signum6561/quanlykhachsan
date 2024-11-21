package com.nhom3_221404.usecase.FilterInvoiceList;

import com.nhom3_221404.dto.FilterInvoiceListResponse;

public interface FilterInvoiceListOutputBoundary {
    void present(FilterInvoiceListResponse response);
    FilterInvoiceListResponse getFilteredInvoices();
}
