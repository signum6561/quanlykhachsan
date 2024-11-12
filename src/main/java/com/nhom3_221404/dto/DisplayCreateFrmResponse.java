package com.nhom3_221404.dto;

import java.util.List;

public class DisplayCreateFrmResponse {
    private List<GetInvoiceTypesOutputDTO> invoiceTypes;

    public DisplayCreateFrmResponse() {
    }

    public DisplayCreateFrmResponse(List<GetInvoiceTypesOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

    public List<GetInvoiceTypesOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<GetInvoiceTypesOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }
}
