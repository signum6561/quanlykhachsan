package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.CreateInvoiceOutDTO;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceOutputBoundary;

public class CreateInvoicePresenter implements CreateInvoiceOutputBoundary {
    private CreateInvoiceOutDTO createInvoiceOutDTO;
    private RuntimeException error;

    @Override
    public void presentData(CreateInvoiceOutDTO createInvoiceOutDTO) {
        this.createInvoiceOutDTO = createInvoiceOutDTO;
    }

    public CreateInvoiceOutDTO getCreateInvoiceOutDTO() {
        return createInvoiceOutDTO;
    }

    @Override
    public void presentError(RuntimeException error) {
        this.error = error;
        System.err.println("Error: " + error.getMessage());
    }

    public RuntimeException getError() {
        return error;
    }

}
