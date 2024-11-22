package com.nhom3_221404.dto;

import java.util.List;

public class GetInvoiceTypesResponse {
    private List<InvoiceTypeOutputDTO> invoiceTypes;
    
    public GetInvoiceTypesResponse() {
    }

    public GetInvoiceTypesResponse(List<InvoiceTypeOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

    public List<InvoiceTypeOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<InvoiceTypeOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }
}
