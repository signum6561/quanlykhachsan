package com.nhom3_221404.database.repository;

import com.nhom3_221404.entity.Invoice;

public class InvoiceCritea {
    private String type;
    private Invoice data;

    public InvoiceCritea() {
    }

    public InvoiceCritea(String type, Invoice data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Invoice getData() {
        return data;
    }
    public void setData(Invoice data) {
        this.data = data;
    }
}
