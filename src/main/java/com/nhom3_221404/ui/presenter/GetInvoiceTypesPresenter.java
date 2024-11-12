package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.GetInvoiceTypesOutputDTO;
import com.nhom3_221404.dto.GetInvoiceTypesResponse;
import com.nhom3_221404.usecase.GetInvoiceTypes.GetInvoiceTypesOutputBoundary;

public class GetInvoiceTypesPresenter implements GetInvoiceTypesOutputBoundary {

    public Result<GetInvoiceTypesResponse> result;

    public GetInvoiceTypesPresenter() {
        result = new Result<>();
    }

    @Override
    public void presentResult(GetInvoiceTypesResponse response) {
        result.setSuccess(response);
    }

    @Override
    public void presentError(Errors error) {
        result.setFailure(error);
    }

    @Override
    public List<GetInvoiceTypesOutputDTO> getInvoiceTypes() {
        if (result.isSuccess()) {
            return result.getValue().getInvoiceTypes();
        }
        return null;
    }
}
