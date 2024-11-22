package com.nhom3_221404.usecase.ViewInvoice;

import com.nhom3_221404.dto.ViewInvoiceResponse;

public interface ViewInvoiceOutputBoundary {
    void present(ViewInvoiceResponse reponse);

    ViewInvoiceResponse getResponse();
}