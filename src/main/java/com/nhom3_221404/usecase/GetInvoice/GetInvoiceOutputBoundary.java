package com.nhom3_221404.usecase.GetInvoice;

import com.nhom3_221404.dto.GetInvoiceOutputDTO;

public interface GetInvoiceOutputBoundary {
    void present(GetInvoiceOutputDTO getInvoiceOutputDTO);

    GetInvoiceOutputDTO getInvoice();
}
