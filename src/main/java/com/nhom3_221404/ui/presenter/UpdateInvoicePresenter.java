package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.UpdateInvoiceOutputDTO;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.InternalDataAccessException;
import com.nhom3_221404.exceptions.RentalHoursOutOfRangeException;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceOutputBoundary;

public class UpdateInvoicePresenter implements UpdateInvoiceOutputBoundary {
    private Result<UpdateInvoiceOutputDTO> result = new Result<>();

    @Override
    public void presentError(Errors error) {
        result.setFailure(error);
    }

    @Override
    public void presentResult(UpdateInvoiceOutputDTO dto) {
        result.setSuccess(dto);
    }

    public UpdateInvoiceOutputDTO getResult()
            throws DateOutOfRangeException, RentalHoursOutOfRangeException, InternalDataAccessException {
                if (!result.isSuccess()) {
                    switch (result.getError()) {
                        case DateOutOfRange:
                            throw new DateOutOfRangeException();
                        case RentalHoursOutOfRange:
                            throw new RentalHoursOutOfRangeException();
                        case InternalDataAccess:
                            throw new InternalDataAccessException();
                        default:
                            return null;
                    }
                }
        return result.getValue();
    }
}