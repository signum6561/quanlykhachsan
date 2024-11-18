package com.nhom3_221404.dto;

public class ViewInvoiceReponse {
    private GetInvoiceOutputDTO getInvoiceOutputDTOs;

    public ViewInvoiceReponse() {
    }

    public ViewInvoiceReponse(GetInvoiceOutputDTO getInvoiceOutputDTOs) {
        this.getInvoiceOutputDTOs = getInvoiceOutputDTOs;
    }

    public GetInvoiceOutputDTO getGetInvoiceOutputDTOs() {
        return getInvoiceOutputDTOs;
    }

    public void setGetInvoiceOutputDTOs(GetInvoiceOutputDTO getInvoiceOutputDTOs) {
        this.getInvoiceOutputDTOs = getInvoiceOutputDTOs;
    }

}
