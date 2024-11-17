package com.nhom3_221404.dto;

public class CountInvoicesByTypeInputDTO {
    private String invoiceType;

    public CountInvoicesByTypeInputDTO(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceType() {
        return invoiceType;
    }
}