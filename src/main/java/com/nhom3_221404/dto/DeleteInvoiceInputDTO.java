package com.nhom3_221404.dto;

public class DeleteInvoiceInputDTO {
    private String invoiceId;
    public DeleteInvoiceInputDTO(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getInvoiceId() {
        return invoiceId;
    }
}