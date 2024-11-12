package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.assertArg;

import com.github.javafaker.Faker;
import com.nhom3_221404.common.Errors;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.GetInvoiceList.GetInvoiceListDatabaseBoundary;
import com.nhom3_221404.usecase.GetInvoiceList.GetInvoiceListInputBoundary;
import com.nhom3_221404.usecase.GetInvoiceList.GetInvoiceListOutputBoundary;
import com.nhom3_221404.usecase.GetInvoiceList.GetInvoiceListUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class GetInvoiceListUseCaseTest {
    InvoiceFactory invoiceFactory;

    @Mock
    GetInvoiceListOutputBoundary presenter;

    GetInvoiceListInputBoundary viewInvoiceListUC;

    @Mock
    GetInvoiceListDatabaseBoundary database;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());
        viewInvoiceListUC = new GetInvoiceListUseCase(presenter, database);
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
    void testViewInvoices_valid() throws Exception {
        when(database.getInvoiceList()).thenReturn(getMockData());

        viewInvoiceListUC.execute();

        verify(presenter).presentResult(assertArg(response -> {
            assertEquals(response.getData().size(), 10);
        }));
    }

    @Test
    void testViewInvoices_dataAccessError() {
        when(database.getInvoiceList()).thenReturn(null);
        
        viewInvoiceListUC.execute();
        
        verify(presenter).presentError(Errors.InternalDataAccess);
    }
}
