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
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.util.IBatisUtil;
import com.nhom3_221404.util.InvoiceFactory;

public class UpdateInvoiceDAOMySqlTest {
    
    private InvoiceRepository invoiceRepository;
    private InvoiceFactory invoiceFactory;
    private UpdateInvoiceDAOMySQL updateInvoiceDB;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
        updateInvoiceDB = new UpdateInvoiceDAOMySQL(invoiceRepository);
    }

    @AfterEach
    void clean() {
        invoiceRepository.deleteAll();
    }

    @Test
    void testUpdateDailyInvoice() {
        InvoiceDaily originalInvoice = invoiceFactory.seedInvoiceDaily();
        invoiceRepository.insert(originalInvoice);

        String newCustomerName = "Updated Customer";
        double newPrice = 200000.0;
        originalInvoice.setCustomerName(newCustomerName);
        originalInvoice.setPrice(newPrice);
        originalInvoice.setRentalDays(5);


        Invoice updatedInvoice = updateInvoiceDB.updateInvoice(originalInvoice);
        
        assertEquals(newCustomerName, updatedInvoice.getCustomerName());
        assertEquals(newPrice, updatedInvoice.getPrice());
        assertEquals(5, ((InvoiceDaily) updatedInvoice).getRentalDays());
    }

    @Test
    void testUpdateHourlyInvoice() {
        InvoiceHourly originalInvoice = invoiceFactory.seedInvoiceHourly();
        invoiceRepository.insert(originalInvoice);

        String newCustomerName = "Updated Customer";
        double newPrice = 150000.0;
        originalInvoice.setCustomerName(newCustomerName);
        originalInvoice.setPrice(newPrice);
        originalInvoice.setRentalHours(10);

        Invoice updatedInvoice = updateInvoiceDB.updateInvoice(originalInvoice);

        assertEquals(newCustomerName, updatedInvoice.getCustomerName());
        assertEquals(newPrice, updatedInvoice.getPrice());
        assertEquals(10, ((InvoiceHourly) updatedInvoice).getRentalHours());
    }

    @Test
    void testGetInvoiceById() {

        Invoice originalInvoice = invoiceFactory.seedRandomInvoice();
        invoiceRepository.insert(originalInvoice);

        Invoice retrievedInvoice = updateInvoiceDB.getInvoiceById(originalInvoice.getId());

        assertEquals(originalInvoice.getId(), retrievedInvoice.getId());
        assertEquals(originalInvoice.getCustomerName(), retrievedInvoice.getCustomerName());
        assertEquals(originalInvoice.getPrice(), retrievedInvoice.getPrice());
    }
}