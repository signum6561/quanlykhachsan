package com.nhom3_221404.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;
import com.nhom3_221404.database.UpdateInvoiceDAOMySql;
import com.nhom3_221404.dto.UpdateInvoiceInputDTO;
import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.exceptions.InternalDataAccessException;
import com.nhom3_221404.ui.presenter.UpdateInvoicePresenter;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceInputBoundary;
import com.nhom3_221404.usecase.UpdateInvoice.UpdateInvoiceUseCase;
import com.nhom3_221404.util.InvoiceFactory;

@ExtendWith(MockitoExtension.class)
public class UpdateInvoiceUseCaseTest {

    @Mock
    UpdateInvoiceDAOMySql database;

    UpdateInvoicePresenter presenter;
    UpdateInvoiceInputBoundary updateInvoiceUC;
    InvoiceFactory invoiceFactory;

    @BeforeEach
    void setUp() {
        presenter = new UpdateInvoicePresenter();
        updateInvoiceUC = new UpdateInvoiceUseCase(presenter, database);
        invoiceFactory = new InvoiceFactory(new Faker());
    }

    @Test
    void testUpdateInvoice_success() {
        Invoice invoice = invoiceFactory.seedRandomInvoice();


        when(database.findInvoiceById(invoice.getId())).thenReturn(invoice);


        when(database.updateInvoice(invoice)).thenReturn(invoice);

        UpdateInvoiceInputDTO inputDTO = new UpdateInvoiceInputDTO(
                invoice.getId(),
                "UpdatedRoomID",
                200.0,
                "UpdatedCustomer",
                invoice.getBilledDate());


        updateInvoiceUC.execute(inputDTO);


        assertEquals(presenter.getOutputDTO().getRoomId(), "UpdatedRoomID");
        assertEquals(presenter.getOutputDTO().getPrice(), 200.0);
        assertEquals(presenter.getOutputDTO().getCustomerName(), "UpdatedCustomer");
    }

    @Test
    void testUpdateInvoice_notFound() {
        when(database.findInvoiceById("invalid-id")).thenReturn(null);

        UpdateInvoiceInputDTO inputDTO = new UpdateInvoiceInputDTO(
                "invalid-id",
                "RoomID",
                150.0,
                "Customer",
                null);

        assertThrows(InternalDataAccessException.class, () -> {
            updateInvoiceUC.execute(inputDTO);
        });
    }

    @Test
    void testUpdateInvoice_databaseError() {
        // Mock lỗi từ database
        when(database.findInvoiceById("any-id")).thenThrow(new RuntimeException("Database error"));

        UpdateInvoiceInputDTO inputDTO = new UpdateInvoiceInputDTO(
                "any-id",
                "RoomID",
                150.0,
                "Customer",
                null);

        assertThrows(InternalDataAccessException.class, () -> {
            updateInvoiceUC.execute(inputDTO);
        });
    }
}
