package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.module.MyModule;
import com.nhom3_221404.util.InvoiceFactory;
import com.nhom3_221404.util.Page;
import com.nhom3_221404.util.Pageable;

public class FilterInvoiceListDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    FilterInvoiceListDAOMySql filterInvoiceListDB;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new MyModule());
        invoiceRepository = injector.getInstance(InvoiceRepository.class);
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        filterInvoiceListDB = new FilterInvoiceListDAOMySql(invoiceRepository);
        pageable = new Pageable(1, 10);
    }

    @Test
    void testFilterInvoiceList_1() {
        String pattern = "IV-";
        Page<Invoice> result = filterInvoiceListDB.getFilteredInvoices(pattern, pageable);
        assertTrue(result.getTotal() > 0);
        assertTrue(result.getData().size() > 0);
    }

    @Test
    void testFilterInvoiceList_2() {
        String pattern = "2024-";
        Page<Invoice> result = filterInvoiceListDB.getFilteredInvoices(pattern, pageable);
        assertTrue(result.getTotal() > 0);
        assertTrue(result.getData().size() > 0);
    }

    @Test
    void testFilterInvoiceList_3() {
        String pattern = "B30";
        Page<Invoice> result = filterInvoiceListDB.getFilteredInvoices(pattern, pageable);
        assertTrue(result.getTotal() > 0);
        assertTrue(result.getData().size() > 0);
    }

    @Test
    void testFilterInvoiceList_4() {
        String pattern = "Jr.";
        Page<Invoice> result = filterInvoiceListDB.getFilteredInvoices(pattern, pageable);
        assertTrue(result.getTotal() > 0);
        assertTrue(result.getData().size() > 0);
    }
}
