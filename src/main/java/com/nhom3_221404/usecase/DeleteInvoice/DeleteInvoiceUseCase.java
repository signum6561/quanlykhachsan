package com.nhom3_221404.usecase.DeleteInvoice;

import com.nhom3_221404.dto.DeleteInvoiceInputDTO;
import com.nhom3_221404.dto.DeleteInvoiceOutputDTO;

public class DeleteInvoiceUseCase implements DeleteInvoiceInputBoundary {
    private DeleteInvoiceDatabaseBoundary databaseBoundary;
    private DeleteInvoiceOutputBoundary outputBoundary;
    public DeleteInvoiceUseCase(DeleteInvoiceDatabaseBoundary databaseBoundary, DeleteInvoiceOutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }
    @Override
    public void execute(DeleteInvoiceInputDTO input) {
        String invoiceId = input.getInvoiceId();
        boolean success = databaseBoundary.deleteInvoice(invoiceId);
        DeleteInvoiceOutputDTO output;
        if (success) {
            output = new DeleteInvoiceOutputDTO(true, "Invoice delete success");
        } else {
            output = new DeleteInvoiceOutputDTO(false, "Failure delete invoice");
        }
        outputBoundary.presentDeleteResult(output);
    }
}