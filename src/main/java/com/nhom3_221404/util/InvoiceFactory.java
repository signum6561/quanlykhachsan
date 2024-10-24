package com.nhom3_221404.util;

import java.time.LocalDateTime;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.github.javafaker.Faker;
import com.github.javafaker.Options;
import com.github.javafaker.service.RandomService;
import com.nhom3_221404.common.InvoiceType;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;

public class InvoiceFactory {
    Faker faker;
    RandomService randomService;
    Options options;

    public InvoiceFactory(Faker faker) {
        this.faker = faker;
        randomService = faker.random();
        options = faker.options();
    }

    private Invoice seedInvoice(Invoice invoice) {
        String[] roomIds = {"B103", "B104", "B607", "B504", "B303"};
        invoice.setId(NanoIdUtils.randomNanoId());
        invoice.setCustomerName(faker.name().fullName());
        invoice.setRoomId(options.nextElement(roomIds));
        invoice.setPrice(faker.number().randomDouble(2, 1000000, 5000000));
        invoice.setBilledDate(LocalDateTime.now());
        return invoice;
    }

    public Invoice seedRandomInvoice() {
        InvoiceType randomType = options.nextElement(InvoiceType.values());
        Invoice invoice = null;
        switch (randomType) {
            case Daily:
                invoice = seedInvoiceDaily();
                break;
            case Hourly:
                invoice = seedInvoiceHourly();
                break;
        }
        return invoice;
    }

    public InvoiceDaily seedInvoiceDaily() {
        InvoiceDaily invoiceDaily = new InvoiceDaily();
        invoiceDaily.setInvoiceType(InvoiceType.Daily);
        invoiceDaily.setRentalDays(faker.random().nextInt(0, 20));
        return (InvoiceDaily) seedInvoice(invoiceDaily);
    }

    public InvoiceHourly seedInvoiceHourly() {
        InvoiceHourly invoiceHourly = new InvoiceHourly();
        invoiceHourly.setInvoiceType(InvoiceType.Hourly);
        invoiceHourly.setRentalHours(faker.random().nextInt(0, 30));
        return (InvoiceHourly) seedInvoice(invoiceHourly);
    }
}
