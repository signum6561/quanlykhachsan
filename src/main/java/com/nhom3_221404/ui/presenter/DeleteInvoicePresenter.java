package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.DeleteInvoiceOutputDTO;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceOutputBoundary;

public class DeleteInvoicePresenter implements DeleteInvoiceOutputBoundary {
    private DeleteInvoiceOutputDTO viewModel;
    @Override
    public void presentSuccess(String message) {
        viewModel = new DeleteInvoiceOutputDTO();
        viewModel.setSuccess(true);
        viewModel.setMessage(message);
    }
    @Override
    public void presentFailure(String message) {
        viewModel = new DeleteInvoiceOutputDTO();
        viewModel.setSuccess(false);
        viewModel.setMessage(message);
    }
    public DeleteInvoiceOutputDTO getViewModel() {
        return viewModel;
    }
}