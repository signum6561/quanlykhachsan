package com.nhom3_221404.usecase.DeleteInvoice;

public interface DeleteInvoiceOutputBoundary {
    void presentSuccess(String message);
    void presentFailure(String message);
}