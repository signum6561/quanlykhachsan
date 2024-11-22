package com.nhom3_221404.external;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.module.MyModule;
import com.nhom3_221404.util.InvoiceFactory;

public class InsertInvoiceTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;

    @BeforeEach
    void setUp() {
        invoiceRepository = Guice.createInjector(new MyModule()).getInstance(InvoiceRepository.class);
        invoiceFactory = new InvoiceFactory(new Faker());
    }

    @Test
    void insertInvoice() {
        for(int i = 0; i < 100; i++) {
            invoiceRepository.insert(invoiceFactory.seedRandomInvoice());
        }
    }
}
