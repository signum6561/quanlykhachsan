package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.GetInvoiceListResponse;
import com.nhom3_221404.usecase.GetInvoiceList.GetInvoiceListOutputBoundary;

public class GetInvoiceListPresenter implements GetInvoiceListOutputBoundary {
    private Result<GetInvoiceListResponse> result = new Result<>();

    @Override
    public void presentResult(GetInvoiceListResponse response) {
        result.setSuccess(response);
    }

    @Override
    public void presentError(Errors error) {
        result.setFailure(error);
    }
}
