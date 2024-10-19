package com.nhom3_221404.usecase.ViewInvoiceList;

import java.util.List;

import com.nhom3_221404.dto.ViewInvoiceOutputDTO;

public interface ViewInvoiceListOutputBoundary {
    void present(List<ViewInvoiceOutputDTO> data);
}
