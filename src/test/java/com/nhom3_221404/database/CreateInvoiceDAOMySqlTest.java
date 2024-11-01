package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.util.IBatisUtil;
import com.nhom3_221404.util.InvoiceFactory;

public class CreateInvoiceDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    CreateInvoiceDAOMySql createInvoiceDB;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
        createInvoiceDB = new CreateInvoiceDAOMySql(invoiceRepository);
    }

    @AfterEach
    void clean() {
        invoiceRepository.deleteAll();
    }

    @Test
    void addInvoice() {
        Invoice invoice = invoiceFactory.seedRandomInvoice();
        createInvoiceDB.addInvoice(invoice);
        Invoice inserted = invoiceRepository.findById(invoice.getId());
        assertEquals(inserted.getId(), invoice.getId());
        assertEquals(inserted.getCustomerName(), invoice.getCustomerName());
    }
}
