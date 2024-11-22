package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.dto.InvoiceTypeOutputDTO;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

public class GetInvoiceTypesPresenter implements GetInvoiceTypesOutputBoundary {

    public List<InvoiceTypeOutputDTO> invoiceTypes;

    @Override
    public void present(GetInvoiceTypesResponse response) {
        invoiceTypes = response.getInvoiceTypes();
    }

    @Override
    public List<InvoiceTypeOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }
}
