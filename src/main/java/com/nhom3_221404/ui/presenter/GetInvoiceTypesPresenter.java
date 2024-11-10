package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

public class GetInvoiceTypesPresenter implements GetInvoiceTypesOutputBoundary {

    private Result<GetInvoiceTypesResponse> result = new Result<>();

    @Override
    public void present(GetInvoiceTypesResponse response) {
        result.setSuccess(response);
    }

    @Override
    public void present(Errors error) {
        result.setError(error);
    }

}
