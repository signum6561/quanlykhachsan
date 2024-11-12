package com.nhom3_221404.usecase.GetInvoiceList;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.GetInvoiceListResponse;

public interface GetInvoiceListOutputBoundary {
    void presentResult(GetInvoiceListResponse response);
    void presentError(Errors error);
}
