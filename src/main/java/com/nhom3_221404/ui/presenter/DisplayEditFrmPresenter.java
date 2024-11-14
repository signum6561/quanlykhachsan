package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.dto.DisplayEditFrmResponse;
import com.nhom3_221404.dto.GetInvoiceOutputDTO;
import com.nhom3_221404.dto.GetInvoiceTypesOutputDTO;
import com.nhom3_221404.usecase.DisplayEditFrm.DisplayEditFrmOutputBoundary;

public class DisplayEditFrmPresenter implements DisplayEditFrmOutputBoundary {
    private List<GetInvoiceTypesOutputDTO> invoiceTypes; 
    private GetInvoiceOutputDTO invoiceDetails; 

    @Override
    public void present(DisplayEditFrmResponse response) {
        invoiceTypes = response.getInvoiceTypes(); 
        invoiceDetails = response.getInvoiceDetails(); 
    }

    public List<GetInvoiceTypesOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }

    public GetInvoiceOutputDTO getInvoiceDetails() {
        return invoiceDetails;
    }
}