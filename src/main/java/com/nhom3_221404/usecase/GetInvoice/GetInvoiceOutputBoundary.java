package com.nhom3_221404.usecase.GetInvoice;

import com.nhom3_221404.dto.InvoiceOutputDTO;

public interface GetInvoiceOutputBoundary {
    void present(InvoiceOutputDTO getInvoiceOutputDTO);

    InvoiceOutputDTO getInvoice();
}
