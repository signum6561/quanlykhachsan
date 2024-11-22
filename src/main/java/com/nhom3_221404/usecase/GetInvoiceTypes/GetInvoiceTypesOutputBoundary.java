package com.nhom3_221404.usecase.GetInvoiceTypes;

import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.dto.InvoiceTypeOutputDTO;

import java.util.List;

public interface GetInvoiceTypesOutputBoundary {
    void present(GetInvoiceTypesResponse response);

    List<InvoiceTypeOutputDTO> getInvoiceTypes();

}
