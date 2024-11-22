package com.nhom3_221404.dto;

import com.nhom3_221404.common.Operation;

public class ManageInvoiceRequest {
    private Operation operation;
    private String pattern;
    private PageRequest pageRequest;
    private String invoiceId;

    public ManageInvoiceRequest() {
        operation = Operation.DEFAULT;
    }

    public ManageInvoiceRequest(Operation operation, String pattern, PageRequest pageRequest, String invoiceId) {
        this.operation = operation;
        this.pattern = pattern;
        this.pageRequest = pageRequest;
        this.invoiceId = invoiceId;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }


    public PageRequest getPageRequest() {
        return this.pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public Operation getOperation() {
        return this.operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
}
