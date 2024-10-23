package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.dto.CreateInvoiceOutDTO;
import com.nhom3_221404.dto.ResponseError;

public interface CreateInvoiceOutputBoundary {
    void presentError(ResponseError responseError);

    void presentData(CreateInvoiceOutDTO createInvoiceOutDTO);
}
