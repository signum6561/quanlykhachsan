package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.dto.InvoiceTypesOutputDTO;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

public class GetInvoiceTypesPresenter implements GetInvoiceTypesOutputBoundary {

    public List<InvoiceTypesOutputDTO> invoiceTypes;

    @Override
    public void present(GetInvoiceTypesResponse response) {
        invoiceTypes = response.getInvoiceTypes();
    }

    @Override
    public List<InvoiceTypesOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }
}
