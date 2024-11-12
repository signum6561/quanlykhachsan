package com.nhom3_221404.usecase.GetInvoiceTypes;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.dto.GetInvoiceTypesOutputDTO;

import java.util.List;

public interface GetInvoiceTypesOutputBoundary {
    void presentResult(GetInvoiceTypesResponse response);

    void presentError(Errors error);

    List<GetInvoiceTypesOutputDTO> getInvoiceTypes();

}
