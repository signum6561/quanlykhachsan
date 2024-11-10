package com.nhom3_221404.usecase.GetInvoiceTypes;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;

public interface GetInvoiceTypesOutputBoundary {
    void present(GetInvoiceTypesResponse response);

    void present(Errors error);
}
