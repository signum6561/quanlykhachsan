package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.AverageInvoiceOutputDTO;
import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.exceptions.InternalDataAccessException;

public class AverageInvoicePresenter {
    private Result<AverageInvoiceOutputDTO> result = new Result<>();

    public void showError(Errors error) {
        result.setFailure(error);
    }

    public void showResult(AverageInvoiceOutputDTO dto) {
        result.setSuccess(dto);
    }

    public AverageInvoiceOutputDTO getResult() throws InternalDataAccessException {
        if (!result.isSuccess()) {
            throw new InternalDataAccessException();
        }
        return result.getValue();
    }
}