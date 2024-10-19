package com.nhom3_221404.ui;

import java.util.List;

import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListOutputBoundary;

public class ViewInvoiceListPresenter implements ViewInvoiceListOutputBoundary {
    
    private List<ViewInvoiceOutputDTO> outputDTOList;

    @Override
    public void present(List<ViewInvoiceOutputDTO> data) {
        outputDTOList = data;
    }

    public List<ViewInvoiceOutputDTO> getOutputDTOList() {
        return outputDTOList;
    }
}
