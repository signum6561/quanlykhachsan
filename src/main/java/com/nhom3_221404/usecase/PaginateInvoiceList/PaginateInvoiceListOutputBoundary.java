package com.nhom3_221404.usecase.PaginateInvoiceList;

import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.PageResponse;

public interface PaginateInvoiceListOutputBoundary {
    void present(PageResponse<InvoiceOutputDTO> response);
}
