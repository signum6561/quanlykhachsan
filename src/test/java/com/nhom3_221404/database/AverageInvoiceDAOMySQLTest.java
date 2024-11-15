package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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
    void calculateAverageInvoice() {

        Invoice invoice1 = invoiceFactory.seedInvoiceDaily(); 
        invoice1.setBilledDate(LocalDate.now()); 
        invoiceRepository.insert(invoice1);
    
        Invoice invoice2 = invoiceFactory.seedInvoiceDaily();
        invoice2.setPrice(200.0);
        invoice2.setBilledDate(LocalDate.now());
        invoiceRepository.insert(invoice2);
    
        Invoice invoice3 = invoiceFactory.seedInvoiceDaily();
        invoice3.setPrice(300.0);
        invoice3.setBilledDate(LocalDate.now());
        invoiceRepository.insert(invoice3);
    
        double average = averageInvoiceDB.calculateAverageInvoice();
        
        double expectedAverage = (invoice1.getTotal() + invoice2.getTotal() + invoice3.getTotal()) / 3;
        assertEquals(expectedAverage, average, 0.001, "Trung bình hóa đơn không chính xác");
    }

    @Test
    void calculateAverageInvoiceNoData() {
        double average = averageInvoiceDB.calculateAverageInvoice();
        assertEquals(0.0, average, 0.001, "Trung bình hóa đơn không chính xác khi không có dữ liệu");
    }
}