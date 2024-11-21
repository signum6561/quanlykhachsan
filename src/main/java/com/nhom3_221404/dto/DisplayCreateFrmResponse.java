package com.nhom3_221404.dto;

import java.util.List;

public class DisplayCreateFrmResponse {
    private List<InvoiceTypesOutputDTO> invoiceTypes;

    public DisplayCreateFrmResponse() {
    }

    public DisplayCreateFrmResponse(List<InvoiceTypesOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

    public List<InvoiceTypesOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<InvoiceTypesOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }
}
