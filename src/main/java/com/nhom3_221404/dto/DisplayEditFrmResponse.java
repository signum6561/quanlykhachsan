package com.nhom3_221404.dto;

import java.util.List;

public class DisplayEditFrmResponse {
    private List<InvoiceTypeOutputDTO> invoiceTypes;
    private InvoiceOutputDTO invoiceDetails;

    public DisplayEditFrmResponse() {
    }

    public DisplayEditFrmResponse(List<InvoiceTypeOutputDTO> invoiceTypes, InvoiceOutputDTO invoiceDetails) {
        this.invoiceTypes = invoiceTypes;
        this.invoiceDetails = invoiceDetails;
    }

    public List<InvoiceTypeOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<InvoiceTypeOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

    public InvoiceOutputDTO getInvoiceDetails() {
        return invoiceDetails;
    }
}