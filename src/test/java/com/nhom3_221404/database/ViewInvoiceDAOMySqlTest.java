package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
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

public class ViewInvoiceDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    ViewInvoiceListDAOMySql viewInvoiceListDB;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
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
