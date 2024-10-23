package com.nhom3_221404.common;

public enum InvoiceType {
    Daily("dl"),
    Hourly("hl");

    private String code;

    private InvoiceType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
