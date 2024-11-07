package com.nhom3_221404.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
import com.nhom3_221404.util.IBatisUtil;
import com.nhom3_221404.util.InvoiceFactory;

public class SearchInvoiceDAOMySqlTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    SearchInvoiceDAOMySql searchInvoiceDB;
    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
        searchInvoiceDB = new SearchInvoiceDAOMySql(invoiceRepository);
    }
    @Test
    void testSearchInvoice_anyPattern() {
        searchInvoiceDB.searchInvoice("hello");
    }

}
