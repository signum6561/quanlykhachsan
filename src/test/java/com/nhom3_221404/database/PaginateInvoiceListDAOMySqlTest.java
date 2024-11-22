package com.nhom3_221404.database;

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

public class PaginateInvoiceListDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    PaginateInvoiceListDAOMySql paginationDB;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new MyModule());
        invoiceRepository = injector.getInstance(InvoiceRepository.class);
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        paginationDB = new PaginateInvoiceListDAOMySql(invoiceRepository);
    }

    @Test
    void testPaginateInvoiceList() {
        Pageable pageable = new Pageable(1, 15);
        Page<Invoice> result =paginationDB.getPaginatedInvoices(pageable);
        assert(result.getTotal() > 0);
        assert(result.getData().size() > 0);
    }
}
