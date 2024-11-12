package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.assertArg;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceDatabaseBoundary;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceInputBoundary;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceOutputBoundary;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class SearchInvoiceUseCaseTest {
    InvoiceFactory invoiceFactory;

    @Mock
    SearchInvoiceOutputBoundary presenter;

    SearchInvoiceInputBoundary searchInvoiceUC;

    @Mock
    SearchInvoiceDatabaseBoundary database;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());
        searchInvoiceUC = new SearchInvoiceUseCase(database, presenter); 
    }

    @Test
    void testSearchInvoice_nullResult() {
        when(database.searchInvoice(anyString())).thenReturn(null);

        searchInvoiceUC.execute("test null result");

        verify(presenter).present(assertArg(response -> {
            assertEquals(response.getData().size(), 0);
        }));
    }

    @Test
    void testSearchInvoice_valid() {
        List<Invoice> mockList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            mockList.add(invoiceFactory.seedRandomInvoice());
        }

        when(database.searchInvoice(anyString())).thenReturn(mockList);

        searchInvoiceUC.execute("test any pattern");

        verify(presenter).present(assertArg(response -> {
            assertEquals(response.getData().size(), 10);
        }));
    }
}
