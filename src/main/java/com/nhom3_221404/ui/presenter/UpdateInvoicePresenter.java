package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceOutputBoundary;

public class UpdateInvoicePresenter implements UpdateInvoiceOutputBoundary {
    private Result<UpdateInvoiceOutputDTO> result = new Result<>();

    @Override
    public void presentError(RuntimeException error) {
        result.setFailure(error);
    }

    @Override
    public void presentResult(UpdateInvoiceOutputDTO dto) {
        result.setSuccess(dto);
    }

    public UpdateInvoiceOutputDTO getResult() throws Exception {
        if (!result.isSuccess()) {
            throw result.getError();
        }
        return result.getValue();
    }
}