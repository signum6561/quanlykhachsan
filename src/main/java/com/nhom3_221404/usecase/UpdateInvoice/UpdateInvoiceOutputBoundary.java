package com.nhom3_221404.usecase.UpdateInvoice;

public interface UpdateInvoiceOutputBoundary {
    void presentError(Exception error);
    void presentResult(String message);
}