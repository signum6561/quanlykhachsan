package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.CountInvoicesByTypeOutputDTO;
import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.exceptions.InternalDataAccessException;

public class CountInvoicesByTypePresenter {
    private Result<CountInvoicesByTypeOutputDTO> result = new Result<>();


    public void presentResult(CountInvoicesByTypeOutputDTO dto) {
        result.setSuccess(dto);
    }


    public void presentError(Errors error) {
        result.setFailure(error);
    }

    public CountInvoicesByTypeOutputDTO getResult() throws InternalDataAccessException {
        if (!result.isSuccess()) {
            throw new InternalDataAccessException();
        }
        return result.getValue();
    }
}