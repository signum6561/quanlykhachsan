package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.common.Errors;
import com.nhom3_221404.common.Result;
import com.nhom3_221404.dto.ViewInvoiceListResponse;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.exceptions.InternalDataAccessException;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListOutputBoundary;

public class ViewInvoiceListPresenter implements ViewInvoiceListOutputBoundary {
    private Result<ViewInvoiceListResponse> result = new Result<>();

    @Override
    public void presentResult(ViewInvoiceListResponse response) {
        result.setSuccess(response);
    }

    @Override
    public void presentError(Errors error) {
        result.setFailure(error);
    }

    public List<ViewInvoiceOutputDTO> getOutputDTOList() throws InternalDataAccessException {
        if(!result.isSuccess()) {
            switch (result.getError()) {
                case InternalDataAccess:
                    throw new InternalDataAccessException();
                default:
                    return null;
            }
        }
        return result.getValue().getData();
    }
}
