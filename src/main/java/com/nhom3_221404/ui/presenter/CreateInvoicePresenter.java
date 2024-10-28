package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.CreateInvoiceOutputDTO;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceOutputBoundary;

public class CreateInvoicePresenter implements CreateInvoiceOutputBoundary {
    private Result<CreateInvoiceOutputDTO> result = new Result<>();

    @Override
    public void presentError(RuntimeException error) {
        result.setFailure(error);
    }

    @Override
    public void presentResult(CreateInvoiceOutputDTO dto) {
        result.setSuccess(dto);
    }

    public CreateInvoiceOutputDTO getResult()
            throws Exception {
        if (!result.isSuccess()) {
            throw result.getError();
        }
        return result.getValue();
    }

}
