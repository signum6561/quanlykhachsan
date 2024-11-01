package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.SearchInvoiceDAOMySql;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.ui.presenter.SearchInvoicePresenter;
import com.nhom3_221404.usecase.SearchInvoice.SearchInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class SearchInvoiceUseCaseTest {
    InvoiceFactory invoiceFactory;
    SearchInvoicePresenter presenter;
    SearchInvoiceUseCase searchInvoiceUC;

    @Mock
    SearchInvoiceDAOMySql database;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());
        presenter = new SearchInvoicePresenter();
        searchInvoiceUC = new SearchInvoiceUseCase(database, presenter); 
    }

    @Test
    void testSearchInvoice_nullResult() {
        when(database.searchInvoice(ArgumentMatchers.anyString())).thenReturn(null);
        searchInvoiceUC.execute("test null result");
        List<ViewInvoiceOutputDTO> invoices = presenter.getSearchResult();
        assertEquals(invoices.size(), 0);
    }

    @Test
    void testSearchInvoice_searchAnyPattern() {
        List<Invoice> mockList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            mockList.add(invoiceFactory.seedRandomInvoice());
        }
        when(database.searchInvoice(ArgumentMatchers.anyString())).thenReturn(mockList);
        searchInvoiceUC.execute("test any pattern");
        List<ViewInvoiceOutputDTO> invoices = presenter.getSearchResult();
        assertEquals(invoices.size(), 10);
    }
}
