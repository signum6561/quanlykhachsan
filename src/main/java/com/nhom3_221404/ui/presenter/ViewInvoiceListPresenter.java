package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.ViewInvoiceListResponse;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListOutputBoundary;

public class ViewInvoiceListPresenter implements ViewInvoiceListOutputBoundary {
    
    private Result<ViewInvoiceListResponse> result;

    @Override
    public void presentResult(ViewInvoiceListResponse response) {
        result = Result.success(response);
    }

    @Override
    public void presentError(RuntimeException error) {
        result = Result.failure(error);
    }

    public List<ViewInvoiceOutputDTO> getOutputDTOList() {
        if(!result.isSuccess()) {
            throw result.getError();
        }
        return result.getValue().getData();
    }
}
