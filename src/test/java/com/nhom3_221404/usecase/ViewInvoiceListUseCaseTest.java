package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.ViewInvoiceListDAOMySql;
import com.nhom3_221404.database.repository.InvoiceRepository;
import com.nhom3_221404.database.repository.InvoiceRepositoryImpl;
import com.nhom3_221404.dto.ViewInvoiceOutputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.ui.presenter.ViewInvoiceListPresenter;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListInputBoundary;
import com.nhom3_221404.usecase.ViewInvoiceList.ViewInvoiceListUseCase;
import com.nhom3_221404.util.IBatisUtil;
import com.nhom3_221404.util.InvoiceFactory;

public class ViewInvoiceListUseCaseTest {
    InvoiceRepository invoiceRepository;
    InvoiceFactory invoiceFactory;
    ViewInvoiceListPresenter presenter;
    ViewInvoiceListInputBoundary viewInvoiceListUC;
    ViewInvoiceListDAOMySql database;

    @BeforeEach
    void setUp() {
        // Set up database connection
        invoiceRepository = new InvoiceRepositoryImpl(IBatisUtil.buildSqlSessionFactoryTest());
        invoiceFactory = new InvoiceFactory(new Faker());

        presenter = new ViewInvoiceListPresenter();
        database = new ViewInvoiceListDAOMySql(invoiceRepository);
        viewInvoiceListUC = new ViewInvoiceListUseCase(presenter, database);

        try {
            invoiceRepository.deleteAll();
            for(int i = 0; i < 10; i++) {
                Invoice invoice = invoiceFactory.seedRandomInvoice();
                invoiceRepository.insert(invoice);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testViewInvoices() throws Exception {
        viewInvoiceListUC.execute();
        List<ViewInvoiceOutputDTO> invoices = presenter.getOutputDTOList();
        assertEquals(invoices.size(), 10);
    }
}
