package com.nhom3_221404.usecase.AverageInvoice;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.database.AverageInvoiceDAOMySQL;
import com.nhom3_221404.dto.AverageInvoiceInputDTO;
import com.nhom3_221404.dto.AverageInvoiceOutputDTO;

public class AverageInvoiceUseCase implements AverageInvoiceInputBoundary {
    private final AverageInvoiceDAOMySQL averageInvoiceDB;
    private final AverageInvoiceOutputBoundary outputBoundary;

    public AverageInvoiceUseCase(AverageInvoiceDAOMySQL averageInvoiceDB, AverageInvoiceOutputBoundary outputBoundary) {
        this.averageInvoiceDB = averageInvoiceDB;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(AverageInvoiceInputDTO inputDTO) {
       
        double averageAmount = averageInvoiceDB.calculateAverageInvoice(inputDTO.getMonth());

        if (averageAmount <= 0) { 
            outputBoundary.presentError(Errors.InvoiceNotFound);
        } else {
            AverageInvoiceOutputDTO outputDTO = new AverageInvoiceOutputDTO(averageAmount);
            outputBoundary.presentResult(outputDTO);
        }
    }
}