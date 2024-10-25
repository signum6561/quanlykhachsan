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

    public static InvoiceType convert(String code) {
        for(InvoiceType type : InvoiceType.values()) {
            if(type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
