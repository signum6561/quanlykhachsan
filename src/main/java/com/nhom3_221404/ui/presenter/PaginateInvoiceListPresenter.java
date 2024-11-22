package com.nhom3_221404.ui.presenter;

import com.google.inject.Singleton;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.dto.PageResponse;
import com.nhom3_221404.usecase.PaginateInvoiceList.PaginateInvoiceListOutputBoundary;

@Singleton
public class PaginateInvoiceListPresenter implements PaginateInvoiceListOutputBoundary {

    PageResponse<InvoiceOutputDTO> paginatedInvoices;

    @Override
    public void present(PageResponse<InvoiceOutputDTO> response) {
        paginatedInvoices = response;
    }

    @Override
    public PageResponse<InvoiceOutputDTO> getPaginatedInvoices() {
        return paginatedInvoices;
    }

}
