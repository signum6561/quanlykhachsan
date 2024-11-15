package com.nhom3_221404.dto;

import java.util.List;

public class ViewInvoiceReponse {
    private List<GetInvoiceOutputDTO> getInvoiceOutputDTOs;

    public ViewInvoiceReponse() {
    }

    public ViewInvoiceReponse(List<GetInvoiceOutputDTO> getInvoiceOutputDTOs) {
        this.getInvoiceOutputDTOs = getInvoiceOutputDTOs;
    }

    public List<GetInvoiceOutputDTO> getGetInvoiceOutputDTOs() {
        return getInvoiceOutputDTOs;
    }

    public void setGetInvoiceOutputDTOs(List<GetInvoiceOutputDTO> getInvoiceOutputDTOs) {
        this.getInvoiceOutputDTOs = getInvoiceOutputDTOs;
    }

}
