package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
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

public class AverageInvoiceDAOMySQLTest {
    private InvoiceRepository invoiceRepository;
    private InvoiceFactory invoiceFactory;
    private AverageInvoiceDAOMySQL averageInvoiceDB;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
        averageInvoiceDB = new AverageInvoiceDAOMySQL(invoiceRepository);
    }

    @AfterEach
    void clean() {
        invoiceRepository.deleteAll();
    }

    @Test
    void testCalculateAverageInvoiceForJanuary() {
        Invoice invoice1 = invoiceFactory.seedInvoiceDaily();
        invoice1.setBilledDate(LocalDate.of(2024, Month.JANUARY, 15));
        invoice1.setPrice(150.0);
        invoiceRepository.insert(invoice1);

        Invoice invoice2 = invoiceFactory.seedInvoiceDaily();
        invoice2.setBilledDate(LocalDate.of(2024, Month.JANUARY, 28));
        invoice2.setPrice(200.0);
        invoiceRepository.insert(invoice2);

        double average = averageInvoiceDB.calculateAverageInvoice(Month.JANUARY);

        double expectedAverage = (invoice1.getTotal() + invoice2.getTotal()) / 2; 
        assertEquals(expectedAverage, average, 0.001, "The calculated average for January should match the expected average.");
    }

}