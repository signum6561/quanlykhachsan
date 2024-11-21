package com.nhom3_221404.dto;

public class PaginateInvoiceListRequest {
    private PageRequest pageRequest;
    private String pattern;

    public PaginateInvoiceListRequest() {
    }

    public PaginateInvoiceListRequest(PageRequest pageRequest, String pattern) {
        this.pageRequest = pageRequest;
        this.pattern = pattern;
    }

    public PageRequest getPageRequest() {
        return this.pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
