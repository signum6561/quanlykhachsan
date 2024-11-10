package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.module.MyModule;
import com.nhom3_221404.util.InvoiceFactory;

public class ViewInvoiceListDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    ViewInvoiceListDAOMySql viewInvoiceListDB;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new MyModule());
        invoiceRepository = injector.getInstance(InvoiceRepository.class);
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        viewInvoiceListDB = new ViewInvoiceListDAOMySql(invoiceRepository);
    }

    @AfterEach
    void clean() {
        invoiceRepository.deleteAll();
    }

    @Test
    void testGetInvoiceList() {
        for(int i = 0; i < 10; i++) {
            Invoice invoice = invoiceFactory.seedRandomInvoice();
            invoiceRepository.insert(invoice);
        }
        List<Invoice> res = viewInvoiceListDB.getInvoiceList();
        assertEquals(res.size(), 10);
    }
}
