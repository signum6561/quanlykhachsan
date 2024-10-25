package com.nhom3_221404.dto;

import java.util.List;

public class ViewInvoiceListResponse {
    private List<ViewInvoiceOutputDTO> data;

    public ViewInvoiceListResponse() {
    }

    public ViewInvoiceListResponse(List<ViewInvoiceOutputDTO> data) {
        this.data = data;
    }

    public List<ViewInvoiceOutputDTO> getData() {
        return data;
    }

    public void setData(List<ViewInvoiceOutputDTO> data) {
        this.data = data;
    }
}
