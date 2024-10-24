package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.ViewInvoiceListDAOMySql;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.exceptions.InternalDataAccessException;
import com.nhom3_221404.ui.presenter.ViewInvoiceListPresenter;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListInputBoundary;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class ViewInvoiceListUseCaseTest {
    InvoiceFactory invoiceFactory;
    ViewInvoiceListPresenter presenter;

    ViewInvoiceListInputBoundary viewInvoiceListUC;

    @Mock
    ViewInvoiceListDAOMySql database;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());

        presenter = new ViewInvoiceListPresenter();
        viewInvoiceListUC = new ViewInvoiceListUseCase(presenter, database);
    }

    private List<Invoice> getMockData() {
        List<Invoice> invoices = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Invoice invoice = invoiceFactory.seedRandomInvoice();
            invoices.add(invoice);
        }
        return invoices;
    }

    @Test
    void testViewInvoices_valid() {
        when(database.getInvoiceList()).thenReturn(getMockData());
        viewInvoiceListUC.execute();
        List<ViewInvoiceOutputDTO> invoices = presenter.getOutputDTOList();
        assertEquals(invoices.size(), 10);
    }

    @Test
    void testViewInvoices_throwsInternalDataAccessException() {
        assertThrows(InternalDataAccessException.class, () -> {
            when(database.getInvoiceList()).thenReturn(null);
            viewInvoiceListUC.execute();
            presenter.getOutputDTOList();
        });
    }
}
