package com.nhom3_221404.ui.presenter;

import java.util.List;

import com.nhom3_221404.dto.SearchInvoiceResponse;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceOutputBoundary;

public class SearchInvoicePresenter implements SearchInvoiceOutputBoundary {
    private SearchInvoiceResponse result;

    @Override
    public void present(SearchInvoiceResponse response) {
        result = response;
    }

    public List<ViewInvoiceOutputDTO> getSearchResult() {
        return result.getData();
    }

}
