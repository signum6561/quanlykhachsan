package com.nhom3_221404.ui.viewModel;

import java.util.List;

import com.google.inject.Singleton;
import com.nhom3_221404.dto.InvoiceOutputDTO;
import com.nhom3_221404.ui.util.AlertFactory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

@Singleton
public class ManageInvoiceViewModel {
    private final ListProperty<InvoiceOutputDTO> invoiceList;
    private final IntegerProperty pageCount;    

    public ManageInvoiceViewModel() {
        this.invoiceList = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.pageCount = new SimpleIntegerProperty(0);
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount.set(pageCount);
    }

    public void setInvoiceList(List<InvoiceOutputDTO> invoiceList) {
        this.invoiceList.set(FXCollections.observableArrayList(invoiceList));
    }

    public void showInfo(String message) {
        AlertFactory.info(message).show();
    }

    public ListProperty<InvoiceOutputDTO> invoiceListProperty() {
        return invoiceList;
    }

    public IntegerProperty pageCountProperty() {
        return pageCount;
    }
}
