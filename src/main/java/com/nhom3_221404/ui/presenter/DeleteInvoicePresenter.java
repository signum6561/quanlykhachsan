package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.DeleteInvoiceOutputDTO;
import com.nhom3_221404.usecase.DeleteInvoice.DeleteInvoiceOutputBoundary;

public class DeleteInvoicePresenter implements DeleteInvoiceOutputBoundary {
    @Override
    public void presentDeleteResult(DeleteInvoiceOutputDTO output) {
        if (output.isSuccess()) {
            System.out.println("Success: " + output.getMessage());
        } else {
            System.out.println("Error: " + output.getMessage());
        }
    }
}