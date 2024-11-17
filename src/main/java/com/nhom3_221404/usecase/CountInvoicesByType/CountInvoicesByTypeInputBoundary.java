package com.nhom3_221404.usecase.CountInvoicesByType;

import com.nhom3_221404.dto.CountInvoicesByTypeInputDTO;

public interface CountInvoicesByTypeInputBoundary {
    void execute(CountInvoicesByTypeInputDTO inputDTO);
}