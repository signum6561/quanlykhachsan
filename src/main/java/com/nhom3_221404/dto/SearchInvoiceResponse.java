package com.nhom3_221404.dto;

import java.util.List;

public class SearchInvoiceResponse {
    private List<ViewInvoiceOutputDTO> data;

    public SearchInvoiceResponse() {
    }

    public SearchInvoiceResponse(List<ViewInvoiceOutputDTO> data) {
        this.data = data;
    }

    public List<ViewInvoiceOutputDTO> getData() {
        return data;
    }

    public void setData(List<ViewInvoiceOutputDTO> data) {
        this.data = data;
    }
}
