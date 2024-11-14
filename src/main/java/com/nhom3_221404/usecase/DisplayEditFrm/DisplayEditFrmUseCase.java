package com.nhom3_221404.usecase.DisplayEditFrm;

import java.util.List;

import com.nhom3_221404.dto.DisplayEditFrmResponse;
import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.dto.GetInvoiceTypesOutputDTO;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmInputBoundary;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmOutputBoundary;
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

        List<GetInvoiceTypesOutputDTO> invoiceTypes = getInvoiceTypesOutputB.getInvoiceTypes();

        getInvoiceInputB.execute(invoiceId);
        
        GetInvoiceOutputDTO invoiceDetails = getInvoiceOutputB.getInvoice();

        displayFrmOutputB.present(new DisplayEditFrmResponse(invoiceTypes, invoiceDetails));
    }
}