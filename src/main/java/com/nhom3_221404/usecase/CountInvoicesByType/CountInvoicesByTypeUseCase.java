package com.nhom3_221404.usecase.CountInvoicesByType;

import com.nhom3_221404.dto.CountInvoicesByTypeInputDTO;
import com.nhom3_221404.dto.CountInvoicesByTypeOutputDTO;
import com.nhom3_221404.common.Errors;

public class CountInvoicesByTypeUseCase implements CountInvoicesByTypeInputBoundary {
    private CountInvoicesByTypeOutputBoundary outputBoundary;
    private CountInvoicesByTypeDatabaseBoundary databaseBoundary;

    public CountInvoicesByTypeUseCase(CountInvoicesByTypeOutputBoundary outputBoundary,
                                       CountInvoicesByTypeDatabaseBoundary databaseBoundary) {
        this.outputBoundary = outputBoundary;
        this.databaseBoundary = databaseBoundary;
    }

    @Override
    public void execute(CountInvoicesByTypeInputDTO inputDTO) {
        String invoiceType = inputDTO.getInvoiceType();
        int invoiceCount = databaseBoundary.countInvoicesByType(invoiceType);

        if (invoiceCount < 0) {
            outputBoundary.presentError(Errors.InternalDataAccess);
            return;
        }

        CountInvoicesByTypeOutputDTO outputDTO = new CountInvoicesByTypeOutputDTO(invoiceType, invoiceCount);
        outputBoundary.presentResult(outputDTO);
    }
}