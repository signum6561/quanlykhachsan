package com.nhom3_221404.usecase.AverageInvoice;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.AverageInvoiceOutputDTO;

public interface AverageInvoiceOutputBoundary {
    void presentResult(AverageInvoiceOutputDTO outputDTO);
    void presentError(Errors error);
}