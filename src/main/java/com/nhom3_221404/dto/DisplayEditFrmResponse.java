package com.nhom3_221404.dto;

import java.util.List;

public class DisplayEditFrmResponse {
    private List<GetInvoiceTypesOutputDTO> invoiceTypes;
    private GetInvoiceOutputDTO invoiceDetails;

    public DisplayEditFrmResponse() {
    }

    public DisplayEditFrmResponse(List<GetInvoiceTypesOutputDTO> invoiceTypes, GetInvoiceOutputDTO invoiceDetails) {
        this.invoiceTypes = invoiceTypes;
        this.invoiceDetails = invoiceDetails;
    }

    public List<GetInvoiceTypesOutputDTO> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<GetInvoiceTypesOutputDTO> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }

    public GetInvoiceOutputDTO getInvoiceDetails() {
        return invoiceDetails;
    }
}
