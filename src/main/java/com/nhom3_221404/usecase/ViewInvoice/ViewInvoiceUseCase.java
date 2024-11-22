package com.nhom3_221404.usecase.ViewInvoice;

import com.google.inject.Inject;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.ViewInvoiceResponse;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceInputBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;

public class ViewInvoiceUseCase implements ViewInvoiceInputBoundary {
    private final ViewInvoiceOutputBoundary viewInvoiceOutputBoundary;
    private final GetInvoiceInputBoundary getInvoiceInputBoundary;
    private final GetInvoiceOutputBoundary getInvoiceOutputBoundary;

    @Inject
    public ViewInvoiceUseCase(ViewInvoiceOutputBoundary viewInvoiceOutputBoundary,
            GetInvoiceInputBoundary getInvoiceInputBoundary, GetInvoiceOutputBoundary getInvoiceOutputBoundary) {
        this.viewInvoiceOutputBoundary = viewInvoiceOutputBoundary;
        this.getInvoiceInputBoundary = getInvoiceInputBoundary;
        this.getInvoiceOutputBoundary = getInvoiceOutputBoundary;
    }

    @Override
    public void execute(String id) {
        getInvoiceInputBoundary.execute(id);

        InvoiceOutputDTO outputDTO = getInvoiceOutputBoundary.getInvoice();

        viewInvoiceOutputBoundary.present(new ViewInvoiceResponse(outputDTO));
    }
}