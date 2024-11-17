package com.nhom3_221404.dto;

public class CountInvoicesByTypeOutputDTO {
    private String invoiceType; 
    private int invoiceCount;   


    public CountInvoicesByTypeOutputDTO() {
    }

    public CountInvoicesByTypeOutputDTO(String invoiceType, int invoiceCount) {
        this.invoiceType = invoiceType;
        this.invoiceCount = invoiceCount;
    }


    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }


    public int getInvoiceCount() {
        return invoiceCount;
    }

    public void setInvoiceCount(int invoiceCount) {
        this.invoiceCount = invoiceCount;
    }
}