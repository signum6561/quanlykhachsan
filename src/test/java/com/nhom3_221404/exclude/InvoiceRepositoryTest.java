package com.nhom3_221404.exclude;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.util.IBatisUtil;
import com.nhom3_221404.util.InvoiceFactory;

@TestInstance(Lifecycle.PER_CLASS)
public class InvoiceRepositoryTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;

    @BeforeAll
    void setUp() throws SQLException {
        invoiceFactory = new InvoiceFactory(new Faker());
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
    }

    @Test
    void insertInvoices() {
        for(int i = 0; i < 10; i++) {
            Invoice invoice = invoiceFactory.seedRandomInvoice();
            assertNotNull(invoiceRepository.insert(invoice));
        }
    }

    @Test
    void getInvoiceList() {
        List<Invoice> invoices = invoiceRepository.findAll();
        assertNotNull(invoices);
    }

    @Test
    void checkExists() {
        InvoiceDaily invoiceDaily = invoiceFactory.seedInvoiceDaily();
        Invoice inserted = invoiceRepository.insert(invoiceDaily);
        assertTrue(invoiceRepository.isExists(inserted.getId()));
    }

    @Test
    void deleteInvoice() {
        InvoiceDaily invoiceDaily = invoiceFactory.seedInvoiceDaily();
        Invoice inserted = invoiceRepository.insert(invoiceDaily);
        invoiceRepository.delete(inserted.getId());
        assertTrue(!invoiceRepository.isExists(inserted.getId()));
    }
}
