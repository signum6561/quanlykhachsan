package com.nhom3_221404.usecase.DisplayEditFrm;

import java.util.List;

import com.google.inject.Inject;
import com.nhom3_221404.dto.DisplayEditFrmResponse;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.InvoiceTypeOutputDTO;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceInputBoundary;
import com.nhom3_221404.usecase.GetInvoice.GetInvoiceOutputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesInputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

public class DisplayEditFrmUseCase implements DisplayEditFrmInputBoundary {
    private final DisplayEditFrmOutputBoundary displayFrmOutputB;
    private final GetInvoiceTypesInputBoundary getInvoiceTypesInputB;
    private final GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB;
    private final GetInvoiceInputBoundary getInvoiceInputB; 
    private final GetInvoiceOutputBoundary getInvoiceOutputB; 

    @Inject
    public DisplayEditFrmUseCase(DisplayEditFrmOutputBoundary displayFrmOutputB, 
                                  GetInvoiceTypesInputBoundary getInvoiceTypesInputB, 
                                  GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB,
                                  GetInvoiceInputBoundary getInvoiceInputB, 
                                  GetInvoiceOutputBoundary getInvoiceOutputB) {
        this.displayFrmOutputB = displayFrmOutputB;
        this.getInvoiceTypesInputB = getInvoiceTypesInputB;
        this.getInvoiceTypesOutputB = getInvoiceTypesOutputB;
        this.getInvoiceInputB = getInvoiceInputB; 
        this.getInvoiceOutputB = getInvoiceOutputB; 
    }

    @Override
    public void execute(String invoiceId) { 

        getInvoiceTypesInputB.execute();

        List<InvoiceTypeOutputDTO> invoiceTypes = getInvoiceTypesOutputB.getInvoiceTypes();

        getInvoiceInputB.execute(invoiceId);

        InvoiceOutputDTO invoiceDetails = getInvoiceOutputB.getInvoice();

        displayFrmOutputB.present(new DisplayEditFrmResponse(invoiceTypes, invoiceDetails));
    }
}