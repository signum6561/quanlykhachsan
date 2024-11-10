package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

public class DeleteInvoiceDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    DeleteInvoiceDAOMySql deleteInvoiceDB;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new MyModule());
        invoiceRepository = injector.getInstance(InvoiceRepository.class);
        deleteInvoiceDB = new DeleteInvoiceDAOMySql(invoiceRepository);
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
    }

    @AfterEach
    void clean() {
        invoiceRepository.deleteAll();
    }

    @Test
    void deleteInvoice_ExistingInvoice_ReturnsTrue() {
        Invoice invoice = invoiceFactory.seedRandomInvoice();
        invoiceRepository.insert(invoice);
        boolean result = deleteInvoiceDB.deleteInvoice(invoice.getId());
        assertTrue(result);
        assertFalse(invoiceRepository.isExists(invoice.getId()));
    }
}