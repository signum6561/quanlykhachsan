package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

public class GetInvoiceDAOMySQLTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    GetInvoiceDAOMySQL getInvoiceDB;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new MyModule());
        invoiceRepository = injector.getInstance(InvoiceRepository.class);
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        getInvoiceDB = new GetInvoiceDAOMySQL(invoiceRepository);
    }

    @Test
    void test_getInvoice() {
        Invoice invoice = invoiceFactory.seedRandomInvoice();
        invoiceRepository.insert(invoice);
        Invoice inserted = getInvoiceDB.getInvoice(invoice.getId());
        assertEquals(inserted, invoice);
    }
}
