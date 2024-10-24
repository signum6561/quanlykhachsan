package com.nhom3_221404.usecase.ViewInvoiceList;

import com.nhom3_221404.dto.ViewInvoiceListResponse;

public interface ViewInvoiceListOutputBoundary {
    void presentResult(ViewInvoiceListResponse response);
    void presentError(RuntimeException error);
}
