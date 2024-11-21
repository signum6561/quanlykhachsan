package com.nhom3_221404.usecase.CreateInvoice;

public interface CreateInvoiceOutputBoundary {
    void presentSuccess(String message);

    void presentError(Exception error);
}
