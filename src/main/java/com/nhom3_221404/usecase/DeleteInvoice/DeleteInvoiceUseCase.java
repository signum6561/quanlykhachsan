package com.nhom3_221404.usecase.DeleteInvoice;

import com.nhom3_221404.dto.DeleteInvoiceInputDTO;

public class DeleteInvoiceUseCase implements DeleteInvoiceInputBoundary {
    private final DeleteInvoiceDatabaseBoundary databaseBoundary;
    private final DeleteInvoiceOutputBoundary outputBoundary;
    public DeleteInvoiceUseCase(DeleteInvoiceDatabaseBoundary databaseBoundary, 
        DeleteInvoiceOutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute(DeleteInvoiceInputDTO input) {
        boolean deleted = databaseBoundary.deleteInvoice(input.getInvoiceId());
        
        if (deleted) {
            outputBoundary.presentSuccess("invoice delete success");
        } else {
            outputBoundary.presentFailure("failure delete invoice");
        }
    }
}