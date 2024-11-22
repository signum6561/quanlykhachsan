package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.ViewInvoiceResponse;
import com.nhom3_221404.usecase.ViewInvoice.ViewInvoiceOutputBoundary;

public class ViewInvoicePresenter implements ViewInvoiceOutputBoundary {

    ViewInvoiceResponse response;

    @Override
    public void present(ViewInvoiceResponse response) {
        this.response = response;
    }

    @Override
    public ViewInvoiceResponse getResponse() {
        return response;
    }

}
