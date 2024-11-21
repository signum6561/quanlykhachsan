package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceOutputBoundary;

public class CreateInvoicePresenter implements CreateInvoiceOutputBoundary {
    private Result<String> result = new Result<>();

    @Override
    public void presentError(Exception error) {
        result.setFailure(error);
    }

    @Override
    public void presentSuccess(String message) {
        result.setSuccess(message);
    }
}
