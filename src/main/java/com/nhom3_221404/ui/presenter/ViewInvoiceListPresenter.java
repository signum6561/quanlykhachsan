package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListOutputBoundary;

public class ViewInvoiceListPresenter implements ViewInvoiceListOutputBoundary {
    
    private Result<List<ViewInvoiceOutputDTO>> result;

    @Override
    public void presentResult(List<ViewInvoiceOutputDTO> data) {
        result = Result.success(data);
    }

    @Override
    public void presentError(RuntimeException error) {
        result = Result.failure(error);
    }

    public List<ViewInvoiceOutputDTO> getOutputDTOList() throws RuntimeException {
        if(!result.isSuccess()) {
            throw result.getError();
        }
        return result.getValue();
    }
}
