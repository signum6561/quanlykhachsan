package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.dto.CreateInvoiceOutDTO;

public interface CreateInvoiceOutputBoundary {
    void presentError(RuntimeException error);

    void presentData(CreateInvoiceOutDTO createInvoiceOutDTO);
}
