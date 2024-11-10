package com.nhom3_221404.dto;

import java.util.List;

public class GetInvoiceTypesResponse {
    private List<InvoiceTypesDTO> invoiceTypes;
    
    public GetInvoiceTypesResponse() {
    }

    public GetInvoiceTypesResponse(List<InvoiceTypesDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

    public List<InvoiceTypesDTO> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<InvoiceTypesDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }
}
