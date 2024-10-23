package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.CreateInvoiceOutDTO;
import com.nhom3_221404.dto.ResponseError;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceOutputBoundary;

public abstract class CreateInvoicePresenter implements CreateInvoiceOutputBoundary {
    private CreateInvoiceOutDTO createInvoiceOutDTO;
    private ResponseError responseError;

    @Override
    public void presentData(CreateInvoiceOutDTO createInvoiceOutDTO) {
        this.createInvoiceOutDTO = createInvoiceOutDTO;
    }

    public CreateInvoiceOutDTO getCreateInvoiceOutDTO() {
        return createInvoiceOutDTO;
    }

    @Override
    public void presentError(ResponseError responseError) {
        this.responseError = responseError;
    }

    public ResponseError getResponseError() {
        return responseError;
    }

}
