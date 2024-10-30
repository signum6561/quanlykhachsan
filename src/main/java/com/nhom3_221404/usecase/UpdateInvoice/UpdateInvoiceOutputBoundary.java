package com.nhom3_221404.usecase.UpdateInvoice;

import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;

public interface UpdateInvoiceOutputBoundary {
    void presentError(RuntimeException error);
    void presentResult(UpdateInvoiceOutputDTO dto);
}