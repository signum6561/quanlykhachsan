package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.DeleteInvoiceOutputDTO;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceOutputBoundary;

public class DeleteInvoicePresenter implements DeleteInvoiceOutputBoundary {
    private DeleteInvoiceOutputDTO viewDelete;
    @Override
    public void presentSuccess(String message) {
        viewDelete = new DeleteInvoiceOutputDTO();
        viewDelete.setSuccess(true);
        viewDelete.setMessage(message);
    }
    @Override
    public void presentFailure(String message) {
        viewDelete = new DeleteInvoiceOutputDTO();
        viewDelete.setSuccess(false);
        viewDelete.setMessage(message);
    }
    public DeleteInvoiceOutputDTO getViewDelete() {
        return viewDelete;
    }
}