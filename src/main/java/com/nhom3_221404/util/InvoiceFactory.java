package com.nhom3_221404.util;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import java.time.ZoneId;

import com.github.javafaker.Faker;
import com.github.javafaker.Options;
import com.github.javafaker.service.RandomService;
import com.nhom3_221404.common.InvoiceConstantType;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.entity.InvoiceType;

public class InvoiceFactory {
    Faker faker;
    RandomService randomService;
    Options options;
    InvoiceIdGenerator idGenerator;

    public InvoiceFactory(Faker faker) {
        this.faker = faker;
        randomService = faker.random();
        options = faker.options();
        idGenerator = new InvoiceIdGenerator();
    }

    public Invoice seedInvoice(Invoice invoice) {
        LocalDate randomDate = faker
            .date()
            .past(300, TimeUnit.DAYS)
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        String[] roomIds = {"B103", "B104", "B607", "B504", "B303"};
        invoice.setId(idGenerator.generate());
        invoice.setCustomerName(faker.name().fullName());
        invoice.setRoomId(options.nextElement(roomIds));
        invoice.setPrice(faker.number().randomDouble(2, 1000000, 5000000));
        invoice.setBilledDate(randomDate);
        return invoice;
    }

    public Invoice seedRandomInvoice() {
        InvoiceConstantType randomType = options.nextElement(InvoiceConstantType.values());
        Invoice invoice = null;
        switch (randomType) {
            case DAILY:
                invoice = seedInvoiceDaily();
                break;
            case HOURLY:
                invoice = seedInvoiceHourly();
                break;
        }
        return invoice;
    }

    public InvoiceDaily seedInvoiceDaily() {
        InvoiceDaily invoice = new InvoiceDaily();
        invoice.setInvoiceType(new InvoiceType("dl", "Daily"));
        invoice.setRentalDays(faker.random().nextInt(1, 20));
        return (InvoiceDaily) seedInvoice(invoice);
    }

    public InvoiceHourly seedInvoiceHourly() {
        InvoiceHourly invoice = new InvoiceHourly();
        invoice.setInvoiceType(new InvoiceType("hl", "Hourly"));
        invoice.setRentalHours(faker.random().nextInt(1, 30));
        return (InvoiceHourly) seedInvoice(invoice);
    }
}
