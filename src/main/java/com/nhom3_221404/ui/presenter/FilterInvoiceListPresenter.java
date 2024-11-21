package com.nhom3_221404.ui.presenter;

import com.nhom3_221404.dto.FilterInvoiceListResponse;
import com.nhom3_221404.usecase.FilterInvoiceList.FilterInvoiceListOutputBoundary;

public class FilterInvoiceListPresenter implements FilterInvoiceListOutputBoundary {
    private FilterInvoiceListResponse response;

    @Override
    public void present(FilterInvoiceListResponse response) {
        this.response = response;
    }

    @Override
    public FilterInvoiceListResponse getFilteredInvoices() {
        return response;
    }

}
