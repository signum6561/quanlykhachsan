package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.dto.CreateInvoiceInputDTO;

public interface CreateInvoiceInputBoundary {
    void execute(CreateInvoiceInputDTO createInvoiceInputDTO);
}
