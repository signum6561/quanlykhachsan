package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.UpdateInvoiceDAOMySQL;
import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.entity.InvoiceDaily;
import com.nhom3_221404.entity.InvoiceHourly;
import com.nhom3_221404.exceptions.DateOutOfRangeException;
import com.nhom3_221404.exceptions.InternalDataAccessException;
import com.nhom3_221404.ui.presenter.UpdateInvoicePresenter;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceInputBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class UpdateInvoiceUseCaseTest {
    UpdateInvoicePresenter presenter;
    UpdateInvoiceInputBoundary updateInvoiceUC;
    InvoiceFactory invoiceFactory;


    @Mock
    private UpdateInvoiceDAOMySQL database;

    private Faker faker;

    @BeforeEach
    void setUp() {
        faker = new Faker();
        invoiceFactory = new InvoiceFactory(faker);
        presenter = new UpdateInvoicePresenter();
        updateInvoiceUC = new UpdateInvoiceUseCase(presenter, database);
    }

    @Test
    void testUpdateHourlyInvoice_Success() throws Exception {
        InvoiceHourly originalInvoice = new InvoiceHourly(20);
        originalInvoice.setId(faker.random().hex());
        originalInvoice.setRoomId("B103");
        originalInvoice.setPrice(50.0);
        originalInvoice.setCustomerName("John Doe");
        originalInvoice.setBilledDate(LocalDate.now());

        when(database.findInvoiceById(originalInvoice.getId())).thenReturn(originalInvoice);
        when(database.updateInvoice(any(Invoice.class))).thenReturn(originalInvoice);

        UpdateInvoiceInputDTO updateDTO = new UpdateInvoiceInputDTO();
        updateDTO.setId(originalInvoice.getId());
        updateDTO.setRoomId("B103");
        updateDTO.setPrice(60.0);
        updateDTO.setCustomerName("Jane Doe");
        updateDTO.setBilledDate(LocalDate.now());
        updateDTO.setRentalHours(25);

        updateInvoiceUC.execute(updateDTO);

        verify(database).updateInvoice(argThat(invoice -> invoice.getRoomId().equals("B103") &&
                invoice.getPrice() == 60.0 &&
                invoice.getCustomerName().equals("Jane Doe") &&
                ((InvoiceHourly) invoice).getRentalHours() == 25));
    }

    @Test
    void testUpdateDailyInvoice_Success() throws Exception {
        InvoiceDaily originalInvoice = new InvoiceDaily(5);
        originalInvoice.setId(faker.random().hex());
        originalInvoice.setRoomId("Room201");
        originalInvoice.setPrice(100.0);
        originalInvoice.setCustomerName("Alice Smith");
        originalInvoice.setBilledDate(LocalDate.now());

        when(database.findInvoiceById(originalInvoice.getId())).thenReturn(originalInvoice);
        when(database.updateInvoice(any(Invoice.class))).thenReturn(originalInvoice);

        UpdateInvoiceInputDTO updateDTO = new UpdateInvoiceInputDTO();
        updateDTO.setId(originalInvoice.getId());
        updateDTO.setRoomId("Room202");
        updateDTO.setPrice(120.0);
        updateDTO.setCustomerName("Bob Johnson");
        updateDTO.setBilledDate(LocalDate.now());
        updateDTO.setRentalDays(7);

        updateInvoiceUC.execute(updateDTO);

        verify(database).updateInvoice(argThat(invoice -> invoice.getRoomId().equals("Room202") &&
                invoice.getPrice() == 120.0 &&
                invoice.getCustomerName().equals("Bob Johnson") &&
                ((InvoiceDaily) invoice).getRentalDays() == 7));
    }

    @Test
    void testUpdateInvoice_NotFound() {
        UpdateInvoiceInputDTO updateDTO = new UpdateInvoiceInputDTO();
        updateDTO.setId("non-existent-id");
        updateDTO.setBilledDate(LocalDate.now());

        when(database.findInvoiceById("non-existent-id")).thenReturn(null);

        assertThrows(InternalDataAccessException.class, () -> {
            updateInvoiceUC.execute(updateDTO);
            presenter.getResult();
        });
    }

    @Test
    void testUpdateInvoice_DateOutOfRange() {
        InvoiceDaily originalInvoice = new InvoiceDaily(5);
        originalInvoice.setId(faker.random().hex());
        originalInvoice.setRoomId("Room201");
        originalInvoice.setPrice(100.0);
        originalInvoice.setCustomerName("Alice Smith");
        originalInvoice.setBilledDate(LocalDate.now());

        when(database.findInvoiceById(originalInvoice.getId())).thenReturn(originalInvoice);

        UpdateInvoiceInputDTO updateDTO = new UpdateInvoiceInputDTO();
        updateDTO.setId(originalInvoice.getId());
        updateDTO.setBilledDate(LocalDate.now().minusMonths(13));

        assertThrows(DateOutOfRangeException.class, () -> {
            updateInvoiceUC.execute(updateDTO);
            presenter.getResult();
        });
    }
}