package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceOutputBoundary;

public class UpdateInvoicePresenter implements UpdateInvoiceOutputBoundary {

    private UpdateInvoiceOutputDTO outputDTO;
    private RuntimeException error;

    @Override
    public void presentSuccess(UpdateInvoiceOutputDTO outputDTO) {
        this.outputDTO = outputDTO;
    }

    @Override
    public void presentError(RuntimeException error) {
        this.error = error;
        throw error; // Re-throw để bắt lỗi trong quá trình xử lý
    }

    public UpdateInvoiceOutputDTO getOutputDTO() {
        return outputDTO;
    }

    public RuntimeException getError() {
        return error;
    }
}
