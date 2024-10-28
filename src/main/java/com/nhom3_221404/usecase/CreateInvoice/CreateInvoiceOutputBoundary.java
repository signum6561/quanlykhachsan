package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.dto.CreateInvoiceOutputDTO;

public interface CreateInvoiceOutputBoundary {
    void presentError(RuntimeException error);

    void presentResult(CreateInvoiceOutputDTO dto);
}
