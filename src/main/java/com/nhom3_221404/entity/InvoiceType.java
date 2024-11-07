package com.nhom3_221404.entity;

import java.util.List;

public class InvoiceType {
    private String code;
    private String name;
    private List<Invoice> invoices;

    public InvoiceType() {
    }

    public InvoiceType(String code) {
        this.code = code;
    }

    public InvoiceType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public List<Invoice> getInvoices() {
        return invoices;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Integer getInvoiceCount() {
        return invoices.size();
    }
}
