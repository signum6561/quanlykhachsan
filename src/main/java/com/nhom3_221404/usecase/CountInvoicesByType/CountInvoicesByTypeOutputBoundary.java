package com.nhom3_221404.usecase.CountInvoicesByType;

import com.nhom3_221404.dto.CountInvoicesByTypeOutputDTO;
import com.nhom3_221404.common.Errors;

public interface CountInvoicesByTypeOutputBoundary {
    void presentError(Errors error);
    void presentResult(CountInvoicesByTypeOutputDTO dto);
}