package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.CreateInvoiceDAOMySQL;
import com.nhom3_221404.dto.CreateInvoiceInputDTO;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.ui.presenter.CreateInvoicePresenter;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceInputBoundary;
import com.nhom3_221404.usecase.CreateInvoice.CreateInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class CreateInvoiceListUseCaseTest {
    InvoiceFactory invoiceFactory;
    CreateInvoicePresenter presenter;

    CreateInvoiceInputBoundary createInvoiceInputBoundary;

    @Mock
    CreateInvoiceDAOMySQL database;

    @BeforeEach
    void setUp() {
        invoiceFactory = new InvoiceFactory(new Faker());

        presenter = new CreateInvoicePresenter();

        createInvoiceInputBoundary = new CreateInvoiceUseCase(presenter, database);
    }

    @Test
    void testCreateInvoiceDaily() {
        InvoiceDaily invoice = invoiceFactory.seedInvoiceDaily();
        // invoice.setRentalDays(7);

        when(database.createInvoiceID(ArgumentMatchers.any())).thenReturn(invoice.getId());
        when(database.findInvoiceID(invoice.getId())).thenReturn(invoice);

        CreateInvoiceInputDTO createInvoiceInputDTO = new CreateInvoiceInputDTO(
                invoice.getRoomId(), invoice.getPrice(),
                invoice.getCustomerName(), invoice.getBilledDate(), invoice.getInvoiceType(), invoice.getRentalDays());
        createInvoiceInputBoundary.execute(createInvoiceInputDTO);

        assertEquals(invoice.getId(), presenter.getCreateInvoiceOutDTO().getId());
        assertEquals(invoice.getRoomId(), presenter.getCreateInvoiceOutDTO().getRoomId());
        assertEquals(invoice.getPrice(), presenter.getCreateInvoiceOutDTO().getPrice());
        assertEquals(invoice.getCustomerName(), presenter.getCreateInvoiceOutDTO().getCustomerName());
        assertEquals(invoice.getBilledDate(), presenter.getCreateInvoiceOutDTO().getBilledDate());
    }

    @Test
    void testCreateInvoiceHourly() {
        InvoiceHourly invoice = invoiceFactory.seedInvoiceHourly();
        // invoice.setRentalHours(7);

        when(database.createInvoiceID(ArgumentMatchers.any())).thenReturn(invoice.getId());
        when(database.findInvoiceID(invoice.getId())).thenReturn(invoice);

        CreateInvoiceInputDTO createInvoiceInputDTO = new CreateInvoiceInputDTO(invoice.getRoomId(),
                invoice.getCustomerName(), invoice.getPrice(), invoice.getBilledDate(), invoice.getInvoiceType(),
                invoice.getRentalHours());
        createInvoiceInputBoundary.execute(createInvoiceInputDTO);

        assertEquals(invoice.getId(), presenter.getCreateInvoiceOutDTO().getId());
        assertEquals(invoice.getRoomId(), presenter.getCreateInvoiceOutDTO().getRoomId());
        assertEquals(invoice.getPrice(), presenter.getCreateInvoiceOutDTO().getPrice());
        assertEquals(invoice.getCustomerName(), presenter.getCreateInvoiceOutDTO().getCustomerName());
        assertEquals(invoice.getBilledDate(), presenter.getCreateInvoiceOutDTO().getBilledDate());
    }
}
