package com.nhom3_221404.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.module.MyModule;
import com.nhom3_221404.util.InvoiceFactory;

public class SearchInvoiceDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    SearchInvoiceDAOMySql searchInvoiceDB;
    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new MyModule());
        invoiceFactory = new InvoiceFactory(new Faker());
        invoiceRepository = injector.getInstance(InvoiceRepository.class);
        searchInvoiceDB = new SearchInvoiceDAOMySql(invoiceRepository);
    }
    @Test
    void testSearchInvoice_anyPattern() {
        searchInvoiceDB.searchInvoice("hello");
    }

}
