package com.nhom3_221404.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.entity.InvoiceType;
import com.nhom3_221404.module.MyModule;
import com.nhom3_221404.util.InvoiceFactory;

public class UpdateInvoiceDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    UpdateInvoiceDAOMySQL updateDB;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new MyModule());
        invoiceRepository = injector.getInstance(InvoiceRepository.class);
        invoiceFactory = new InvoiceFactory(new Faker(new Locale("vi")));
        updateDB = new UpdateInvoiceDAOMySQL(invoiceRepository);
    }

    @Test
    void testUpdate_basic() {
        InvoiceDaily invoice = invoiceFactory.seedInvoiceDaily();
        invoiceRepository.insert(invoice);
        invoice.setCustomerName("Nguyen Van A");
        invoice.setPrice(323542.23);
        invoice.setRoomId("B102");
        invoice.setRentalDays(8);
        updateDB.updateInvoice(invoice);
        Invoice updated = invoiceRepository.findById(invoice.getId());
        assertEquals(invoice, updated);
    }

    @Test
    void testUpdate_changeType() {
        InvoiceDaily invoice = invoiceFactory.seedInvoiceDaily();
        invoiceRepository.insert(invoice);
        InvoiceHourly invoiceHourly = new InvoiceHourly();
        invoiceHourly.setId(invoice.getId());
        invoiceHourly.setCustomerName(invoice.getCustomerName());
        invoiceHourly.setPrice(invoice.getPrice());
        invoiceHourly.setRoomId(invoice.getRoomId());
        invoiceHourly.setBilledDate(invoice.getBilledDate());
        invoiceHourly.setRentalHours(8);
        invoiceHourly.setInvoiceType(new InvoiceType("hl", "Hourly"));
        updateDB.updateInvoice(invoiceHourly);

        Invoice updated = invoiceRepository.findById(invoice.getId());
        assertEquals(invoiceHourly, updated);
    }
}
