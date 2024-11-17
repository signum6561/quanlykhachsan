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

public class CountInvoicesByRoomDAOMySQLTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    CountInvoicesByTypeDAOMySQL countInvoicesByRoomDB;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
        countInvoicesByRoomDB = new CountInvoicesByTypeDAOMySQL(invoiceRepository);
    }

    @AfterEach
    void clean() {
        invoiceRepository.deleteAll();
    }

    @Test
    void countInvoicesByRoom() {

        Invoice invoice1 = invoiceFactory.seedRandomInvoice();
        invoice1.setRoomId("room1");
        invoiceRepository.insert(invoice1);
 
        Invoice invoice2 = invoiceFactory.seedRandomInvoice();
        invoice2.setRoomId("room1");
        invoiceRepository.insert(invoice2);

        Invoice invoice3 = invoiceFactory.seedRandomInvoice();
        invoice3.setRoomId("room2");
        invoiceRepository.insert(invoice3);

        int countRoom1 = countInvoicesByRoomDB.countInvoicesByType("room1");
        assertEquals(2, countRoom1);

        int countRoom2 = countInvoicesByRoomDB.countInvoicesByType("room2");
        assertEquals(1, countRoom2);

        int countRoom3 = countInvoicesByRoomDB.countInvoicesByType("room3");
        assertEquals(0, countRoom3);
    }
}