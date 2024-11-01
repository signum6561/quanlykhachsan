package com.nhom3_221404.usecase.DeleteInvoice;

public interface DeleteInvoiceOutputBoundary {
    void presentSuccess(String string);
    void presentFailure(String errorMessage);
}
