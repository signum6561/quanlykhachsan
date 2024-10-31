package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.exceptions.InvoiceNotFoundException;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceOutputBoundary;

public class ViewInvoicePresent implements ViewInvoiceOutputBoundary {
    private Result<ViewInvoiceOutputDTO> result = new Result<>();

    @Override
    public void presentData(ViewInvoiceOutputDTO viewInvoiceOutputDTO) {
        result.setSuccess(viewInvoiceOutputDTO);
    }

    public ViewInvoiceOutputDTO getViewInvoice()
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
}
