package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;

public class GetInvoicePresenter implements GetInvoiceOutputBoundary {
    private InvoiceOutputDTO invoiceDto;

    @Override
    public void present(InvoiceOutputDTO getInvoiceOutputDTO) {
        invoiceDto = getInvoiceOutputDTO;
    }

    @Override
    public InvoiceOutputDTO getInvoice() {
        return invoiceDto;
    }
}
