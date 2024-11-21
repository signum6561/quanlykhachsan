package com.nhom3_221404.dto;

import java.util.List;

import com.nhom3_221404.entity.Invoice;

public class FilterInvoiceListResponse {
    private List<Invoice> data;
    private int total;

    public FilterInvoiceListResponse() {
    }

    public FilterInvoiceListResponse(List<Invoice> data, int total) {
        this.data = data;
        this.total = total;
    }

    public List<Invoice> getData() {
        return data;
    }

    public void setData(List<Invoice> data) {
        this.data = data;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
