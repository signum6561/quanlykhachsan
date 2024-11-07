package com.nhom3_221404.usecase.DeleteInvoice;

import com.nhom3_221404.dto.DeleteInvoiceInputDTO;

public interface DeleteInvoiceInputBoundary {
    void execute(DeleteInvoiceInputDTO input);
}