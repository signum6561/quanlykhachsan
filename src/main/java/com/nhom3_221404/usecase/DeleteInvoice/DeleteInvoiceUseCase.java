package com.nhom3_221404.usecase.DeleteInvoice;

import com.nhom3_221404.constant.StringConst;

public class DeleteInvoiceUseCase implements DeleteInvoiceInputBoundary {
    private final DeleteInvoiceDatabaseBoundary databaseBoundary;
    private final DeleteInvoiceOutputBoundary outputBoundary;
    
    public DeleteInvoiceUseCase(DeleteInvoiceDatabaseBoundary databaseBoundary, DeleteInvoiceOutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }
    
    @Override
    public void execute(String id) {
        try {
            databaseBoundary.deleteInvoice(id);
            outputBoundary.presentSuccess(StringConst.SUCCESS_DELETE_INVOICE);
        } catch (Exception e) {
            outputBoundary.presentSuccess(StringConst.FAILED_DELETE_INVOICE);
        }
    }
}