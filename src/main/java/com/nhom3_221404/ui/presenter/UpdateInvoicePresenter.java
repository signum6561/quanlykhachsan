package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceOutputBoundary;

public class UpdateInvoicePresenter implements UpdateInvoiceOutputBoundary {
    private Result<String> result = new Result<>();

    @Override
    public void presentError(Exception error) {
        result.setFailure(error);
    }

    @Override
    public void presentResult(String message) {
        result.setSuccess(message);
    }
}
