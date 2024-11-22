package com.nhom3_221404.dto;

public class ViewInvoiceResponse {
    private InvoiceOutputDTO invoiceOutputDTO;

    public ViewInvoiceResponse() {
    }

    public ViewInvoiceResponse(InvoiceOutputDTO invoiceOutputDTO) {
        this.invoiceOutputDTO = invoiceOutputDTO;
    }

    public InvoiceOutputDTO getInvoiceOutputDTO() {
        return this.invoiceOutputDTO;
    }

    public void setInvoiceOutputDTO(InvoiceOutputDTO invoiceOutputDTO) {
        this.invoiceOutputDTO = invoiceOutputDTO;
    }

}
