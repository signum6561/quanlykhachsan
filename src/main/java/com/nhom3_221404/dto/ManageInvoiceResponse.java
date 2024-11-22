package com.nhom3_221404.dto;

import java.util.List;

public class ManageInvoiceResponse {
    private List<InvoiceOutputDTO> invoiceList;
    private Integer lastPage;

    public ManageInvoiceResponse() {
    }

    public ManageInvoiceResponse(List<InvoiceOutputDTO> invoiceList, Integer lastPage) {
        this.invoiceList = invoiceList;
        this.lastPage = lastPage;
    }

    public List<InvoiceOutputDTO> getInvoiceList() {
        return this.invoiceList;
    }

    public void setInvoiceList(List<InvoiceOutputDTO> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public Integer getLastPage() {
        return this.lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }


}
