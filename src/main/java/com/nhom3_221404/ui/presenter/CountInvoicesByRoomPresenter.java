package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.CountInvoicesByRoomOutputDTO;
import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.exceptions.InternalDataAccessException;

public class CountInvoicesByRoomPresenter {
    private Result<CountInvoicesByRoomOutputDTO> result = new Result<>();

    public void presentError(Errors error) {
        result.setFailure(error);
    }

    public void presentResult(CountInvoicesByRoomOutputDTO dto) {
        result.setSuccess(dto);
    }

    public CountInvoicesByRoomOutputDTO getResult() throws InternalDataAccessException {
        if (!result.isSuccess()) {
            throw new InternalDataAccessException(); 
        }
        return result.getValue();
    }
}