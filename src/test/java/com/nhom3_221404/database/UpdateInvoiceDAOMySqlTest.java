package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
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

public class UpdateInvoiceDAOMySqlTest {
    private InvoiceRepository invoiceRepository;
    private InvoiceFactory invoiceFactory;
    private UpdateInvoiceDAOMySQL updateInvoiceDB;
    private Invoice existingInvoice;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
        updateInvoiceDB = new UpdateInvoiceDAOMySQL(invoiceRepository);
        
        existingInvoice = invoiceFactory.seedRandomInvoice();
        invoiceRepository.insert(existingInvoice);
    }

    @AfterEach
    void clean() {
        invoiceRepository.deleteAll();
    }

    @Test
    void getInvoiceList() {
        List<Invoice> invoices = invoiceRepository.findAll();
        assertNotNull(invoices);
    }

    @Test
    void updateInvoice() {
        LocalDate newDate = LocalDate.now();
        String newName = "Updated Invoice Name"; 

        existingInvoice.setBilledDate(newDate); 
        existingInvoice.setCustomerName(newName); 


        updateInvoiceDB.updateInvoice(existingInvoice);

        Invoice updatedInvoice = invoiceRepository.findById(existingInvoice.getId());
        
        assertNotNull(updatedInvoice);
        assertEquals(newDate, updatedInvoice.getBilledDate()); 
        assertEquals(newName, updatedInvoice.getCustomerName()); 
    }
}