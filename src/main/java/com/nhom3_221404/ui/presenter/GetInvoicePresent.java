package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.exceptions.InvoiceNotFoundException;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;

public class GetInvoicePresent implements GetInvoiceOutputBoundary {
    private Result<GetInvoiceOutputDTO> result = new Result<>();

    public GetInvoiceOutputDTO getInvoice()
            throws InvoiceNotFoundException {
        if (!result.isSuccess()) {
            switch (result.getError()) {
                case InvoiceNotFound:
                    throw new InvoiceNotFoundException();

                default:
                    return null;
            }
        }
        return result.getValue();
    }

    @Override
    public void presentData(GetInvoiceOutputDTO getInvoiceOutputDTO) {
        result.setSuccess(getInvoiceOutputDTO);
    }
}
