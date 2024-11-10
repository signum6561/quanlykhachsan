package com.nhom3_221404.usecase.CreateInvoice;

import com.nhom3_221404.common.Errors;

public interface CreateInvoiceOutputBoundary {
    void presentSuccess(String message);

    void presentError(Errors error);
}
