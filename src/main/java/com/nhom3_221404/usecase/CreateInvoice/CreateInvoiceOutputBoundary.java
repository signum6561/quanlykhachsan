package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.dto.CreateInvoiceOutputDTO;
import com.nhom3_221404.common.Errors;

public interface CreateInvoiceOutputBoundary {
    void presentError(Errors error);

    void presentResult(CreateInvoiceOutputDTO dto);
}
