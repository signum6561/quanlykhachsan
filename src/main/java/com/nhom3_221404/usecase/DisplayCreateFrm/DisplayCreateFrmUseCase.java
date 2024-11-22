package com.nhom3_221404.usecase.DisplayCreateFrm;

import java.util.List;

import com.google.inject.Inject;
import com.nhom3_221404.dto.DisplayCreateFrmResponse;
import com.nhom3_221404.dto.InvoiceTypeOutputDTO;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesInputBoundary;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

public class DisplayCreateFrmUseCase implements DisplayCreateFrmInputBoundary {
    private final DisplayCreateFrmOutputBoundary displayFrmOutputB;
    private final GetInvoiceTypesInputBoundary getInvoiceTypesInputB;
    private final GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB;

    @Inject
    public DisplayCreateFrmUseCase(DisplayCreateFrmOutputBoundary displayFrmOutputB, GetInvoiceTypesInputBoundary getInvoiceTypesInputB, GetInvoiceTypesOutputBoundary getInvoiceTypesOutputB) {
        this.displayFrmOutputB = displayFrmOutputB;
        this.getInvoiceTypesInputB = getInvoiceTypesInputB;
        this.getInvoiceTypesOutputB = getInvoiceTypesOutputB;
    }

    @Override
    public void execute() {
        //2
        getInvoiceTypesInputB.execute();

        //3-4
        List<InvoiceTypeOutputDTO> invoiceTypes = getInvoiceTypesOutputB.getInvoiceTypes();
        
        //5
        displayFrmOutputB.present(new DisplayCreateFrmResponse(invoiceTypes));
    }
}
