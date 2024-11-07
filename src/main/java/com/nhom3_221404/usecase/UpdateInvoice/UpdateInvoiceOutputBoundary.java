package com.nhom3_221404.usecase.UpdateInvoice;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;

public interface UpdateInvoiceOutputBoundary {
    void presentError(Errors error);
    void presentResult(UpdateInvoiceOutputDTO dto);
}