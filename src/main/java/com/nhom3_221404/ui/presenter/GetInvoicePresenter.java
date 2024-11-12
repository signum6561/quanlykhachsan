package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;

public class GetInvoicePresenter implements GetInvoiceOutputBoundary {
    private GetInvoiceOutputDTO invoiceDto;

    @Override
    public void present(GetInvoiceOutputDTO getInvoiceOutputDTO) {
        invoiceDto = getInvoiceOutputDTO;
    }

    @Override
    public GetInvoiceOutputDTO getInvoice() {
        return invoiceDto;
    }
}
