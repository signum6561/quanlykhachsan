package com.nhom3_221404.usecase.ViewInvoice;

import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.dto.ViewInvoiceReponse;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceInputBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;

public class ViewInvoiceUseCase implements ViewInvoiceInputBoundary {
    private final ViewInvoiceOutputBoundary viewInvoiceOutputBoundary;
    private final GetInvoiceInputBoundary getInvoiceInputBoundary;
    private final GetInvoiceOutputBoundary getInvoiceOutputBoundary;

    public ViewInvoiceUseCase(ViewInvoiceOutputBoundary viewInvoiceOutputBoundary,
            GetInvoiceInputBoundary getInvoiceInputBoundary, GetInvoiceOutputBoundary getInvoiceOutputBoundary) {
        this.viewInvoiceOutputBoundary = viewInvoiceOutputBoundary;
        this.getInvoiceInputBoundary = getInvoiceInputBoundary;
        this.getInvoiceOutputBoundary = getInvoiceOutputBoundary;
    }

    @Override
    public void execute(String invoiceIds) {
        getInvoiceInputBoundary.execute(invoiceIds);
        GetInvoiceOutputDTO outputDTO = getInvoiceOutputBoundary.getInvoice();
        if (outputDTO != null) {
            viewInvoiceOutputBoundary.prensent(new ViewInvoiceReponse(outputDTO));
        }
    }
}
